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
        val alUserOverview = ArrayList<UserOverview>()
        var user_id: Int = 0
        var drink_id: Int = 0

        /*// Für alle Benutzer ein Objekt in der ArrayList alUserOverview (welche dann angezeigt wird), erstellen
        for(i in alUserDrinks.indices){
            drink_id = alUserDrinks[i].drinkID

            if (alUserDrinks[i].userID != user_id){
                if(alUsers.size > 0){
                    val indexUser = alUsers.size - 1
                    alUserOverview.add(UserOverview(alUsers[indexUser].id, alUsers[indexUser].firstName, alUsers[indexUser].lastName, alDrinks))
                }

                // Für den neuen Benutzer neu aufbauen
                alDrinks.clear()
                user_id = alUserDrinks[i].userID
                alUsers.add(database.userDao().getSingleUser(user_id))
                alDrinks.add(database.drinkDao().getSingleDrink(drink_id))
            } else {
                alDrinks.add(database.drinkDao().getSingleDrink(drink_id))
            }
        }

        // Für den letzten User auch noch erstellen
        if(alUsers.size > 0){
            val indexUser = alUsers.size - 1
            alUserOverview.add(UserOverview(alUsers[indexUser].id, alUsers[indexUser].firstName, alUsers[indexUser].lastName, alDrinks))
        }*/


        for (i in alUsers.indices) {
            val sum = database.userDrinksDao().getUnpaid(alUsers[i].id)
            alUserOverview.add(UserOverview(alUsers[i].id, alUsers[i].firstName, alUsers[i].lastName, sum))
        }

        // Sortieren der Liste nach Vorname, Nachname
        alUserOverview.sortWith(compareBy({ it.firstName }))

        setContent(alUserOverview)
    }

    private fun setContent(alUserOverview: ArrayList<UserOverview>) {
        val tableUebersicht: TableLayout = findViewById(R.id.tableUebersicht)

        for(i in alUserOverview.indices){
            val tableRow: TableRow = TableRow(this)
            val tvName: TextView = TextView(this)
            val tvGesamt: TextView = TextView(this)



            tvName.text = "${alUserOverview[i].firstName} ${alUserOverview[i].lastName} (${alUserOverview[i].sumAmount} €)"
            tvName.setTextAppearance(R.style.textStyle)
            tvGesamt.text = "${alUserOverview[i].sumAmount} €"
            tvGesamt.setTextAppearance(R.style.textStyle)

            tableRow.addView(tvName)
            tableRow.addView(tvGesamt)

            tableUebersicht.addView(tableRow)
        }

    }

    override fun onBackPressed() {

    }
}