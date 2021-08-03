package huette.kasse.activities

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import huette.kasse.R
import huette.kasse.UserOverview
import huette.kasse.data.AppDatabase
import huette.kasse.data.entities.Drink
import huette.kasse.data.entities.User

class Uebersicht : AppCompatActivity() {
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.uebersicht)

        database = AppDatabase.getDatabase(application)

        generateContent()
    }

    private fun generateContent() {
        val alUserDrinks = database.userDrinksDao().getUsersWithData()
        val alUsers = database.userDao().getAllUsersList()
        val alDrinks = ArrayList<Drink>()
        //val alUserOverview = ArrayList<UserOverview>()
        var user_id: Int = 0
        var drink_id: Int = 0

        /*for (i in alUsers.indices) {
            val sum = database.userDrinksDao().getUnpaid(alUsers[i].id)
            alUserOverview.add(UserOverview(alUsers[i].id, alUsers[i].firstName, alUsers[i].lastName, sum))
        }

        // Sortieren der Liste nach Vorname, Nachname
        alUserOverview.sortWith(compareBy({ it.firstName }))*/
        val allDrinks = database.drinkDao().getAllDrinksList()
        val allUsers = database.userDao().getAllUsersList()

        setContent(allUsers, allDrinks)
    }

    private fun setContent(allUsers: List<User>, allDrinks: List<Drink>) {
        val tableUebersicht: TableLayout = findViewById(R.id.tableUebersicht)
        val firstRow: TableRow = TableRow(this)

        val tvName: TextView = TextView(this)
        tvName.text = "Name"
        tvName.setTextAppearance(R.style.headlineStyle)
        firstRow.addView(tvName)

        for (i in allDrinks.indices) {
            val tvHeader: TextView = TextView(this)
            tvHeader.text = "${allDrinks[i].drinkName} (${allDrinks[i].price} €)"
            tvHeader.setTextAppearance(R.style.headlineStyle)
            firstRow.addView(tvHeader)
        }

        val tvSumme: TextView = TextView(this)
        tvSumme.text = "Gesamtbetrag (€)"
        tvSumme.setTextAppearance(R.style.headlineStyle)
        firstRow.addView(tvSumme)

        tableUebersicht.addView(firstRow)

        for (i in allUsers.indices) {
            val tableRow: TableRow = TableRow(this)
            val tvName: TextView = TextView(this)

            val sumAmount = database.userDrinksDao().getUnpaid(allUsers[i].id)

            tvName.text = "${allUsers[i].firstName} ${allUsers[i].lastName} (${sumAmount} €)"
            tvName.setTextAppearance(R.style.textStyle)
            tableRow.addView(tvName)

            for (j in allDrinks.indices) {
                val tvDrink: TextView = TextView(this)
                val drinkAmount =
                    database.userDrinksDao().getDrinkAmount(allUsers[i].id, allDrinks[j].id)
                tvDrink.text = "${drinkAmount}"
                tvDrink.setTextAppearance(R.style.textStyle)
                tableRow.addView(tvDrink)
            }

            val tvGesamt: TextView = TextView(this)
            tvGesamt.text = "${sumAmount} €"
            tvGesamt.setTextAppearance(R.style.textStyle)
            tableRow.addView(tvGesamt)

            tableUebersicht.addView(tableRow)
        }

    }

    override fun onBackPressed() {

    }
}