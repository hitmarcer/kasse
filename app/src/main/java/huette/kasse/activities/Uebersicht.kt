package huette.kasse.activities

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import huette.kasse.R
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
        database.userDrinksDao().getUsersWithData()
        database.userDao().getAllUsersList()
        ArrayList<Drink>()
        //val alUserOverview = ArrayList<UserOverview>()

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
        val sumPrice = database.userDrinksDao().getAllUnpaidDouble()
        val sumUserAmount = database.userDrinksDao().getAmountUsers()
        var rowBorderColor = 1

        val tableUebersicht: TableLayout = findViewById(R.id.tableUebersicht)
        val firstRow = TableRow(this)

        val tvName = TextView(this)
        tvName.text = " Name "
        tvName.setTextAppearance(R.style.headlineStyle)
        firstRow.addView(tvName)

        for (i in allDrinks.indices) {
            val tvHeader = TextView(this)
            tvHeader.text = " ${allDrinks[i].drinkName} (${String.format("%.2f", allDrinks[i].price)} €) "
            tvHeader.setTextAppearance(R.style.headlineStyle)
            tvHeader.setBackgroundResource(R.drawable.colborder)
            firstRow.addView(tvHeader)
        }

        val tvSumme = TextView(this)
        tvSumme.text = " Gesamt (${String.format("%.2f", sumPrice)} €) "
        tvSumme.setTextAppearance(R.style.headlineStyle)
        tvSumme.setBackgroundResource(R.drawable.colborder)
        firstRow.addView(tvSumme)

        tableUebersicht.addView(firstRow)

        for (i in allUsers.indices) {
            val tableRow = TableRow(this)
            var tvName = TextView(this)

            val sumAmount = database.userDrinksDao().getUnpaid(allUsers[i].id)

            tvName.text = " ${allUsers[i].firstName} ${allUsers[i].lastName} (${String.format("%.2f", sumAmount)} €) "
            tvName.setTextAppearance(R.style.textStyle)

            tvName = setBackgroundFirstColBorder(tvName, rowBorderColor)

            tableRow.addView(tvName)

            for (j in allDrinks.indices) {
                var tvDrink = TextView(this)
                val drinkAmount =
                    database.userDrinksDao().getDrinkAmount(allUsers[i].id, allDrinks[j].id)
                tvDrink.text = " $drinkAmount "
                tvDrink.setTextAppearance(R.style.textStyle)

                tvDrink = setBackgroundRowBorder(tvDrink, rowBorderColor)

                tableRow.addView(tvDrink)
            }

            var tvGesamt = TextView(this)
            tvGesamt.text = " ${String.format("%.2f", sumAmount)} € "
            tvGesamt.setTextAppearance(R.style.textStyle)

            tvGesamt = setBackgroundRowBorder(tvGesamt, rowBorderColor)

            tableRow.addView(tvGesamt)

            tableRow.setBackgroundResource(R.drawable.tableborder)
            tableUebersicht.addView(tableRow)

            rowBorderColor += 1
        }

        val lastRow = TableRow(this)

        var tvNamesGesamt = TextView(this)
        tvNamesGesamt.text = " $sumUserAmount Personen "
        tvNamesGesamt.setTextAppearance(R.style.headlineStyle)

        tvNamesGesamt = setBackgroundFirstColBorder(tvNamesGesamt, rowBorderColor)

        lastRow.addView(tvNamesGesamt)

        for(i in allDrinks.indices) {
            var tvDrinkAmount = TextView(this)
            tvDrinkAmount.text = " ${database.userDrinksDao().getSumDrinkAmount(allDrinks[i].id)} "
            tvDrinkAmount.setTextAppearance(R.style.headlineStyle)
            tvDrinkAmount = setBackgroundRowBorder(tvDrinkAmount, rowBorderColor)
            lastRow.addView(tvDrinkAmount)
        }

        var tvGesamtAmount = TextView(this)
        tvGesamtAmount.text = (" ${String.format("%.2f", sumPrice)} € ")
        tvGesamtAmount.setTextAppearance(R.style.headlineStyle)
        tvGesamtAmount = setBackgroundRowBorder(tvGesamtAmount, rowBorderColor)
        lastRow.addView(tvGesamtAmount)

        tableUebersicht.addView(lastRow)

    }

    private fun setBackgroundRowBorder(tv: TextView, line: Int): TextView {
        // Bei gerade Zeile weiß, bei ungerade grau hinterlegen
        if(line.mod(2) == 0){
            tv.setBackgroundResource(R.drawable.rowborderwhite)
        } else {
            tv.setBackgroundResource(R.drawable.rowbordergrey)
        }
        return tv
    }

    private fun setBackgroundFirstColBorder(tv: TextView, line: Int): TextView {
        // Bei gerade Zeile weiß, bei ungerade grau hinterlegen
        if(line.mod(2) == 0){
            tv.setBackgroundResource(R.drawable.firstcolborderwhite)
        } else {
            tv.setBackgroundResource(R.drawable.firstcolbordergrey)
        }
        return tv
    }

    override fun onBackPressed() {

    }
}