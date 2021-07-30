package huette.kasse

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddDrink : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_drink)

        val btnAddDrink2: Button = findViewById(R.id.btnAddDrink2)
        val tfDrinkName: EditText = findViewById(R.id.tfDrinkName)
        val tfPrice: EditText = findViewById(R.id.tfPrice)

        val recyclerViewAddDrink: RecyclerView = findViewById(R.id.recyclerViewAddDrink)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, Variables.alDrinks)

        recyclerViewAddDrink.adapter = drinksAdapter
        recyclerViewAddDrink.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        btnAddDrink2.setOnClickListener() {
            val drinkName: String = tfDrinkName.text.toString()
            val price: Double = tfPrice.text.toString().toDouble()
            val error: Int = Variables.addDrink(drinkName, price)

            if (error == 0) {
                Toast.makeText(
                    applicationContext, "$drinkName angelegt",
                    Toast.LENGTH_SHORT
                ).show()

                tfDrinkName.text.clear()
                tfPrice.text.clear()

                drinksAdapter.notifyDataSetChanged()
            } else if (error == 1) {
                Toast.makeText(
                    applicationContext, "$drinkName ist schon vorhanden",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (error == 2) {
                Toast.makeText(
                    applicationContext, "Getränk eingeben",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (error == 3) {
                Toast.makeText(
                    applicationContext, "Preis eingeben",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    applicationContext, "Unknown error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    override fun onBackPressed() {

    }
}