package huette.kasse.activities

import android.os.Bundle
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
    lateinit var tvAddDrinkToUser: TextView
    lateinit var fullName: String
    var userPosition: Int = 0

    val alDrinksUndo = ArrayList<UserDrinks>()
    val alDrinksRedo = ArrayList<UserDrinks>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_drink_to_user)

        database = AppDatabase.getDatabase(this)

        val recyclerViewAddDrinkToUser: RecyclerView = findViewById(R.id.recyclerViewAddDrinkToUser)

        val drinksAdapter = DrinksAdapter(this, this)
        //val namesAdapter: NamesAdapter = NamesAdapter(this, this)

        val btnUndo: ImageButton = findViewById(R.id.btnUndo)
        val btnRedo: ImageButton = findViewById(R.id.btnRedo)

        recyclerViewAddDrinkToUser.adapter = drinksAdapter
        recyclerViewAddDrinkToUser.layoutManager =
            GridLayoutManager(this, Variables.cols)

        // drinkViewModel
        val drinkViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)

        drinkViewModel.getAllDrinks.observe(this, { drinks ->
            drinksAdapter.setData(drinks)
        })

        tvAddDrinkToUser = findViewById(R.id.tvAddDrinkUser)
        fullName = Variables.user.firstName + " " + Variables.user.lastName
        userPosition = Variables.position
        tvAddDrinkToUser.setText(
            "${fullName} (${
                String.format("%.2f", database.userDrinksDao().getUnpaid(Variables.user.id))
            } €)"
        )

        btnUndo.setOnClickListener() {
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

        btnRedo.setOnClickListener() {
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
        Variables.drink = drinks.get(position)

        val userDrink =
            UserDrinks(Variables.user.id, Variables.drink.id, System.currentTimeMillis())

        // In Datenbank einfügen
        database.userDrinksDao().addDrinkToUser(userDrink)

        // Zwischenspeichern, um rückgängig machen zu ermöglichen
        alDrinksUndo.add(userDrink)

        val unpaid = database.userDrinksDao().getUnpaid(Variables.user.id)
        val unpaidString = String.format("%.2f", unpaid)
        // Text von TextView aktualisieren
        tvAddDrinkToUser.setText(
            "${fullName} (${unpaidString} €)"
        )

        Toast.makeText(this, "${drinks[position].drinkName} hinzugefügt", Toast.LENGTH_SHORT).show()

    }

    private fun undo(): Boolean {
        if (alDrinksUndo.size > 0) {
            // Von Datenbank löschen
            // Immer nur den letzten, also an Stelle size - 1
            val index: Int = alDrinksUndo.size - 1
            database.userDrinksDao().deleteDrinkFromUser(
                alDrinksUndo[index].userID,
                alDrinksUndo[index].drinkID,
                alDrinksUndo[index].timeStamp
            )
            alDrinksRedo.add(
                UserDrinks(
                    alDrinksUndo[index].userID,
                    alDrinksUndo[index].drinkID,
                    alDrinksUndo[index].timeStamp
                )
            )
            alDrinksUndo.removeAt(index)

            // Text von TextView aktualisieren
            tvAddDrinkToUser.setText(
                "${fullName} (${
                    String.format("%.2f", database.userDrinksDao().getUnpaid(Variables.user.id))
                } €)"
            )

            return true
        } else {
            return false
        }
    }

    private fun redo(): Boolean {
        return if (alDrinksRedo.size > 0) {
            // Letzter auf Datenbank schreiben
            val index: Int = alDrinksRedo.size - 1
            val userDrink = UserDrinks(
                alDrinksRedo[index].userID,
                alDrinksRedo[index].drinkID,
                System.currentTimeMillis()
            )
            alDrinksRedo.removeAt(index)
            database.userDrinksDao().addDrinkToUser(userDrink)
            alDrinksUndo.add(userDrink)

            // Text von TextView aktualisieren
            tvAddDrinkToUser.setText(
                "${fullName} (${
                    String.format("%.2f", database.userDrinksDao().getUnpaid(Variables.user.id))
                } €)"
            )

            return true
        } else {
            return false
        }
    }

    override fun onBackPressed() {

    }
}