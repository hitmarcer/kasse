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

        database = AppDatabase.getDatabase(this)

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
        var sumAmount = 0.0
        var sumCredit = 0.0
        //val sumPrice = database.userDrinksDao().getAllUnpaidDouble()
        val sumUserAmount = database.userDrinksDao().getAmountUsers()
        var rowBorderColor = 1

        val tableUebersicht: TableLayout = findViewById(R.id.tableUebersicht)
        val firstRow = TableRow(this)

        // Kopfzeile links "Name"
        val tvName = TextView(this)
        tvName.text = " Name "
        tvName.setTextAppearance(R.style.headlineStyle)
        firstRow.addView(tvName)

        // Alle Getränke in Kopfzeile schreiben
        for (i in allDrinks.indices) {
            val tvHeader = TextView(this)
            tvHeader.text = " ${allDrinks[i].drinkName} (${String.format("%.2f", allDrinks[i].price)} €) "
            tvHeader.setTextAppearance(R.style.headlineStyle)
            tvHeader.setBackgroundResource(R.drawable.colborder)
            firstRow.addView(tvHeader)
        }

        // Gesamtsumme in Kopfzeile als Text hinzufügen
        val tvSumme = TextView(this)
        tvSumme.text = " Gesamt "
        tvSumme.setTextAppearance(R.style.headlineStyle)
        tvSumme.setBackgroundResource(R.drawable.colborder)
        firstRow.addView(tvSumme)

        // Guthaben zu erster Zeile hinzufügen
        val tvSumCredit = TextView(this)
        tvSumCredit.text = " Guthaben "
        tvSumCredit.setTextAppearance(R.style.headlineStyle)
        tvSumCredit.setBackgroundResource(R.drawable.colborder)
        firstRow.addView(tvSumCredit)

        tableUebersicht.addView(firstRow)

        for (i in allUsers.indices) {
            val tableRow = TableRow(this)
            var tvName = TextView(this)
            val sumPerson: Double = database.userDao().getUnpaidAmount(allUsers[i].id)
            val creditPerson: Double = database.userDao().getCredit(allUsers[i].id)

            //val sumAmount = database.userDao().getUnpaidAmount(allUsers[i].id)

            tvName.text = " ${allUsers[i].firstName} ${allUsers[i].lastName} (${String.format("%.2f", sumPerson)} €) "
            tvName.setTextAppearance(R.style.textStyle)

            tvName = setBackgroundFirstColBorder(tvName, rowBorderColor)

            tableRow.addView(tvName)

            sumAmount += database.userDao().getUnpaidAmount(allUsers[i].id)
            sumCredit += database.userDao().getCredit(allUsers[i].id)

            for (j in allDrinks.indices) {
                var tvDrink = TextView(this)
                val drinkAmount =
                    database.userDrinksDao().getDrinkAmount(allUsers[i].id, allDrinks[j].id)

                tvDrink.text = " $drinkAmount "
                tvDrink.setTextAppearance(R.style.textStyle)

                tvDrink = setBackgroundRowBorder(tvDrink, rowBorderColor)

                tableRow.addView(tvDrink)
            }

            // Pro Zeile Gesamtsumme für diese Person
            var tvGesamt = TextView(this)
            tvGesamt.text = " ${String.format("%.2f", sumPerson)} € "
            tvGesamt.setTextAppearance(R.style.textStyle)

            tvGesamt = setBackgroundRowBorder(tvGesamt, rowBorderColor)

            tableRow.addView(tvGesamt)

            // Pro Zeile Gesamtsumme für diese Person
            var tvCreditPerson = TextView(this)
            tvCreditPerson.text = " ${String.format("%.2f", creditPerson)} € "
            tvCreditPerson.setTextAppearance(R.style.textStyle)

            tvCreditPerson = setBackgroundRowBorder(tvCreditPerson, rowBorderColor)

            tableRow.addView(tvCreditPerson)

            tableRow.setBackgroundResource(R.drawable.tableborder)
            tableUebersicht.addView(tableRow)

            rowBorderColor += 1
        }

        // letzte Reihe unten links Summe Personen
        val lastRow = TableRow(this)

        var tvNamesGesamt = TextView(this)
        tvNamesGesamt.text = " $sumUserAmount Personen (${String.format("%.2f", sumAmount)} €) "
        tvNamesGesamt.setTextAppearance(R.style.headlineStyle)

        tvNamesGesamt = setBackgroundFirstColBorder(tvNamesGesamt, rowBorderColor)

        lastRow.addView(tvNamesGesamt)

        // letzte Reihe Betrag für die einzelnen Getränke
        for(i in allDrinks.indices) {
            var tvDrinkAmount = TextView(this)
            tvDrinkAmount.text = " ${database.userDrinksDao().getSumDrinkAmount(allDrinks[i].id)} "
            tvDrinkAmount.setTextAppearance(R.style.headlineStyle)
            tvDrinkAmount = setBackgroundRowBorder(tvDrinkAmount, rowBorderColor)
            lastRow.addView(tvDrinkAmount)
        }

        // Oben rechts Gesamt setzen, da dies erst nach der Schleife gemacht werden kann (in der Schleife wird addiert)
        tvSumme.text = " Gesamt (${String.format("%.2f", sumAmount)} €) "

        // Oben rechts Guthaben setzen, da dies erst nach der Schleife gemacht werden kann (in der Schleife wird addiert)
        tvSumCredit.text = " Guthaben (${String.format("%.2f", sumCredit)} €) "

        // Gesamt unten rechts
        var tvGesamtAmount = TextView(this)
        tvGesamtAmount.text = (" ${String.format("%.2f", sumAmount)} € ")
        tvGesamtAmount.setTextAppearance(R.style.headlineStyle)
        tvGesamtAmount = setBackgroundRowBorder(tvGesamtAmount, rowBorderColor)
        lastRow.addView(tvGesamtAmount)

        // Guthaben unten rechts
        var tvGesamtCredit = TextView(this)
        tvGesamtCredit.text = (" ${String.format("%.2f", sumCredit)} € ")
        tvGesamtCredit.setTextAppearance(R.style.headlineStyle)
        tvGesamtCredit = setBackgroundRowBorder(tvGesamtCredit, rowBorderColor)
        lastRow.addView(tvGesamtCredit)

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
}