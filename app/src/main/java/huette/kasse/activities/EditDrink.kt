package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.data.AppDatabase
import huette.kasse.data.entities.Drink

class EditDrink : AppCompatActivity() {
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_drink)

        database = AppDatabase.getDatabase(application)

        val btnEdit: Button = findViewById(R.id.btnEdit)
        //val tfDrinkName: EditText = findViewById(R.id.tfDrinkNameEdit)
        val tvDrinKEdit: TextView = findViewById(R.id.tvDrinkEdit)
        val tfPrice: EditText = findViewById(R.id.tfDrinkPriceEdit)

        tvDrinKEdit.text = Variables.drink.drinkName
        tfPrice.setText(Variables.drink.price.toString())
        tfPrice.setSelection(tfPrice.text.toString().length)

        btnEdit.setOnClickListener {
            val drink = Variables.drink

            // Programm bricht ab, wenn im Textfeld nichts drin steht und Methode toDouble() ausgeführt wird
            val newPrice: Double = if (tfPrice.text.toString() == "") {
                0.0
            } else {
                tfPrice.text.toString().toDouble()
            }

            when (addDrink(drink, newPrice)) {
                0 -> {
                    Toast.makeText(
                        this, "${drink.drinkName} geändert",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, EditDrinks::class.java))
                }
                1 -> {
                    Toast.makeText(
                        this, "Nicht geändert: gleicher Preis",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                3 -> {
                    Toast.makeText(
                        this, "Preis eingeben",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        this, "Unknown error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun addDrink(drink: Drink, newPrice: Double): Int {

        when {
            newPrice > 0.0 -> {
                // Doppelte checken
                val drinkCheck = database.drinkDao().getSingleDrinkNotDeleted(drink.id)

                if(drinkCheck.price == newPrice){
                    return 1
                }

                // Drink davor auf gelöscht setzen
                database.drinkDao().setDrinkDeleted(drink.id)
                // Drink neu hinzufügen, um die alten noch berechnen zu können
                database.drinkDao().addDrink(Drink(drink.drinkName, newPrice))
                return 0
            }
            newPrice <= 0 -> {
                return 3
            }
            else -> {
                return 10
            }
        }

    }


    override fun onBackPressed() {

    }
}