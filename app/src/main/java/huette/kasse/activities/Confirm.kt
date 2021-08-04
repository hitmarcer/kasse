package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.data.AppDatabase

class Confirm : AppCompatActivity() {
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirm)

        database = AppDatabase.getDatabase(application)

        val btnYes: Button = findViewById(R.id.btnYes)
        val btnNo: Button = findViewById(R.id.btnNo)
        val tvConfirm: TextView = findViewById(R.id.tvConfirm)

        val position: Int = Variables.position

        // Function 2: Benutzer wirklich löschen
        // Function 5: Drink löschen
        // Function 8: Auf bezahlt setzen

        if (Variables.function == 2) {
            val firstName: String = Variables.user.firstName
            val lastName: String = Variables.user.lastName
            tvConfirm.setText("${firstName} ${lastName} wirklich löschen?")
        } else if (Variables.function == 5) {
            tvConfirm.setText("${Variables.drink.drinkName} wirklich löschen?")
        } else if (Variables.function == 8) {
            val firstName: String = Variables.user.firstName
            val lastName: String = Variables.user.lastName
            tvConfirm.setText("${firstName} ${lastName} (${String.format("%.2f", database.userDrinksDao().getUnpaid(Variables.user.id))} €) wirklich auf bezahlt setzen?")
        }

        btnYes.setOnClickListener() {
            if (Variables.function == 2) {
                val firstName: String = Variables.user.firstName
                val lastName: String = Variables.user.lastName
                val user_id = Variables.user.id
                // Von Datenbank löschen / auf gelöscht setzen
                database.userDao().setUserDeleted(user_id)
                Toast.makeText(this, "${firstName} ${lastName} wurde gelöscht", Toast.LENGTH_SHORT)
                    .show()

                startActivity(Intent(this, DeleteUser::class.java))
            } else if (Variables.function == 5) {
                // Von Datenbank löschen / auf gelöscht setzen
                database.drinkDao().setDrinkDeleted(Variables.drink.id)
                Toast.makeText(this, "${Variables.drink.drinkName} wurde gelöscht", Toast.LENGTH_SHORT).show()
                //Variables.alDrinkOlds.removeAt(position)
                startActivity(Intent(this, DeleteDrink::class.java))
            } else if (Variables.function == 8) {
                val firstName: String = Variables.user.firstName
                val lastName: String = Variables.user.lastName
                Toast.makeText(this, "${firstName} ${lastName} wurde auf bezahlt gesetzt", Toast.LENGTH_SHORT).show()
                database.userDrinksDao().setPaid(Variables.user.id)
                startActivity(Intent(this, Pay::class.java))
            }
        }

        btnNo.setOnClickListener() {
            if (Variables.function == 2) {
                val firstName: String = Variables.user.firstName
                val lastName: String = Variables.user.lastName
                Toast.makeText(this, "${firstName} ${lastName} wurde nicht gelöscht", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DeleteUser::class.java))
            } else if (Variables.function == 5) {
                Toast.makeText(this, "${Variables.drink.drinkName} wurde nicht gelöscht", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DeleteDrink::class.java))
            } else if (Variables.function == 8) {
                val firstName: String = Variables.user.firstName
                val lastName: String = Variables.user.lastName
                Toast.makeText(this, "${firstName} ${lastName} wurde nicht auf bezahlt gesetzt", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Pay::class.java))
            }
        }
    }

    override fun onBackPressed() {

    }
}