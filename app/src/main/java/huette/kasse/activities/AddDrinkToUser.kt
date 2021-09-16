package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.DrinksAdapter
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.data.AppDatabase
import huette.kasse.data.entities.Drink
import huette.kasse.data.entities.UserDrinks
import huette.kasse.data.viewmodels.DrinksViewModel

class AddDrinkToUser : AppCompatActivity(), DrinksAdapter.OnItemClickListener {
    lateinit var database: AppDatabase
    private lateinit var tvAddDrinkToUser: TextView
    private lateinit var fullName: String
    private lateinit var tvCredit: TextView
    private var userPosition: Int = 0

    private lateinit var tvAddedDrinks: TextView

    private val alDrinksUndo = ArrayList<UserDrinks>()
    private val alDrinksRedo = ArrayList<UserDrinks>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_drink_to_user)

        tvAddedDrinks = findViewById(R.id.addedDrinks)
        tvCredit = findViewById(R.id.tvCreditNow)

        database = AppDatabase.getDatabase(this)

        val recyclerViewAddDrinkToUser: RecyclerView = findViewById(R.id.recyclerViewAddDrinkToUser)

        val drinksAdapter = DrinksAdapter(this, this)
        //val namesAdapter: NamesAdapter = NamesAdapter(this, this)

        val btnUndo: ImageButton = findViewById(R.id.btnUndo)
        val btnRedo: ImageButton = findViewById(R.id.btnRedo)
        val btnAddCredit: Button = findViewById(R.id.btnAddCredit)

        val userID = Variables.user.id
        val credit = database.userDao().getCredit(userID)

        tvCredit.text = "Guthaben: " + String.format("%.2f", credit) + " €"

        recyclerViewAddDrinkToUser.adapter = drinksAdapter
        recyclerViewAddDrinkToUser.layoutManager =
            GridLayoutManager(this, Variables.cols)

        // drinkViewModel
        val drinkViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)

        drinkViewModel.getAllDrinks.observe(this, { drinks ->
            drinksAdapter.setData(drinks)
        })

        // Name anzeigen
        tvAddDrinkToUser = findViewById(R.id.tvAddDrinkUser)
        fullName = Variables.user.firstName + " " + Variables.user.lastName
        userPosition = Variables.position
        tvAddDrinkToUser.text = "$fullName (${
            String.format("%.2f", database.userDao().getUnpaidAmount(Variables.user.id))
        } €)"

        btnAddCredit.setOnClickListener {
            startActivity(Intent(this, Credit::class.java))
        }

        btnUndo.setOnClickListener {
            if (undo()) {
                Toast.makeText(this, "Rückgängig gemacht", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Kann nicht rückgängig gemacht werden (keine Daten vorhanden)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnRedo.setOnClickListener {
            if (redo()) {
                Toast.makeText(this, "Wiederholt", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Kann nicht wiederholt werden (keine Daten vorhanden)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun OnItemClick(position: Int, drinks: List<Drink>) {
        Variables.position = position
        Variables.drink = drinks[position]

        val userDrink =
            UserDrinks(Variables.user.id, Variables.drink.id, System.currentTimeMillis())

        // In Datenbank einfügen
        database.userDrinksDao().addDrinkToUser(userDrink)

        // Zwischenspeichern, um rückgängig machen zu ermöglichen
        alDrinksUndo.add(userDrink)

        var unpaid: Double = database.userDao().getUnpaidAmount(Variables.user.id)
        var credit: Double = database.userDao().getCredit(Variables.user.id)

        if (credit > 0) {
            credit -= Variables.drink.price
            if (credit < 0){
                unpaid = credit * (-1)
                credit = 0.0
                database.userDrinksDao().setPaid(Variables.user.id)
            } else {
                unpaid = 0.0
                database.userDrinksDao().setPaid(Variables.user.id)
            }
        } else {
            unpaid += Variables.drink.price
        }

        database.userDao().setCredit(Variables.user.id, credit)
        database.userDao().setUnpaidAmount(Variables.user.id, unpaid)

        val unpaidString = String.format("%.2f", unpaid)
        val creditString = String.format("%.2f", credit)
        // Text von TextView aktualisieren
        tvAddDrinkToUser.text = "$fullName (${unpaidString} €)"
        tvCredit.text = "Guthaben: $creditString €"

        addToMid()
        alDrinksRedo.clear()

        //Toast.makeText(this, "${drinks[position].drinkName} hinzugefügt", Toast.LENGTH_SHORT).show()

    }

    private fun addToMid() {
        tvAddedDrinks.text = ""
        for(i in alDrinksUndo.indices) {
            val drinkName = database.drinkDao().getSingleDrink(alDrinksUndo[i].drinkID).drinkName
            if (i == 0) {
                tvAddedDrinks.text = drinkName
            } else {
                tvAddedDrinks.text = "${tvAddedDrinks.text}, ${drinkName}"
            }
        }
    }

    private fun subFromMid() {
        var text: String = tvAddedDrinks.text.toString()

        if (text.contains(',')) {
            text = text.substringBeforeLast(',')
        } else {
            text = ""
        }
        tvAddedDrinks.text = text
    //tvAddedDrinks.text = tvAddedDrinks.text.toString().substring(0, tvAddedDrinks.text.lastIndexOf(',', 0, false))
    }

    private fun undo(): Boolean {
        if (alDrinksUndo.size > 0) {
            // Von Datenbank löschen
            // Immer nur den letzten, also an Stelle size - 1
            val index: Int = alDrinksUndo.size - 1
            var credit = database.userDao().getCredit(alDrinksUndo[index].userID)
            var unpaid = database.userDao().getUnpaidAmount(alDrinksUndo[index].userID)

            database.userDrinksDao().deleteDrinkFromUser(
                alDrinksUndo[index].userID,
                alDrinksUndo[index].drinkID,
                alDrinksUndo[index].timeStamp
            )

            if (unpaid > 0) {
                unpaid -= database.drinkDao().getSingleDrink(alDrinksUndo[index].drinkID).price

                if (unpaid < 0) {
                    credit = unpaid * (-1)
                    unpaid = 0.0
                    database.userDrinksDao().setPaid(Variables.user.id)
                } else {
                    credit = 0.0
                }
            } else {
                credit += database.drinkDao().getSingleDrink(alDrinksUndo[index].drinkID).price
                database.userDrinksDao().setPaid(Variables.user.id)
            }

            database.userDao().setCredit(Variables.user.id, credit)
            database.userDao().setUnpaidAmount(Variables.user.id, unpaid)

            alDrinksRedo.add(
                UserDrinks(
                    alDrinksUndo[index].userID,
                    alDrinksUndo[index].drinkID,
                    alDrinksUndo[index].timeStamp
                )
            )
            alDrinksUndo.removeAt(index)

            // Text von TextView aktualisieren
            tvAddDrinkToUser.text = "$fullName (${
                String.format("%.2f", database.userDao().getUnpaidAmount(Variables.user.id))
            } €)"

            tvCredit.text = "Guthaben: " + String.format("%.2f", credit) + " €"

            subFromMid()

            return true
        } else {
            return false
        }
    }

    private fun redo(): Boolean {
        return if (alDrinksRedo.size > 0) {
            // Letzter auf Datenbank schreiben
            val index: Int = alDrinksRedo.size - 1
            var credit = database.userDao().getCredit(alDrinksRedo[index].userID)
            var unpaid = database.userDao().getUnpaidAmount(alDrinksRedo[index].userID)

            val userDrink = UserDrinks(
                alDrinksRedo[index].userID,
                alDrinksRedo[index].drinkID,
                System.currentTimeMillis()
            )

            if (credit > 0) {
                credit -= database.drinkDao().getSingleDrink(alDrinksRedo[index].drinkID).price

                if (credit < 0){
                    unpaid = credit * (-1)
                    credit = 0.0
                    database.userDrinksDao().setPaid(Variables.user.id)
                } else {
                    unpaid = 0.0
                    database.userDrinksDao().setPaid(Variables.user.id)
                }
            } else {
                unpaid += database.drinkDao().getSingleDrink(alDrinksRedo[index].drinkID).price
            }

            database.userDao().setCredit(Variables.user.id, credit)
            database.userDao().setUnpaidAmount(Variables.user.id, unpaid)

            alDrinksRedo.removeAt(index)
            database.userDrinksDao().addDrinkToUser(userDrink)
            alDrinksUndo.add(userDrink)

            // Text von TextView aktualisieren
            tvAddDrinkToUser.text = "$fullName (${
                String.format("%.2f", database.userDao().getUnpaidAmount(Variables.user.id))
            } €)"

            tvCredit.text = "Guthaben: " + String.format("%.2f", credit) + " €"

            addToMid()

            true
        } else {
            false
        }
    }

    override fun onBackPressed() {

    }
}