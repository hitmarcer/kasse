package huette.kasse.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.DrinksAdapter
import huette.kasse.R
import huette.kasse.data.viewmodels.DrinksViewModel
import huette.kasse.data.entities.Drink

class AddDrink : AppCompatActivity(), DrinksAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_drink)

        val btnAddDrink2: Button = findViewById(R.id.btnAddDrink2)
        val tfDrinkName: EditText = findViewById(R.id.tfDrinkName)
        val tfPrice: EditText = findViewById(R.id.tfPrice)

        val recyclerViewAddDrink: RecyclerView = findViewById(R.id.recyclerViewAddDrink)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, this)

        recyclerViewAddDrink.adapter = drinksAdapter
        recyclerViewAddDrink.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val drinksViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)

        drinksViewModel.getAllDrinks.observe(this, Observer { users ->
            drinksAdapter.setData(users)
        })

        btnAddDrink2.setOnClickListener() {
            val drinkName: String = tfDrinkName.text.toString()
            var price: Double = 0.0

            // Programm bricht ab, wenn im Textfeld nichts drin steht und Methode toDouble() ausgeführt wird
            if(tfPrice.text.toString().equals("")) {
                price = 0.0
            } else {
                price = tfPrice.text.toString().toDouble()
            }

            val error: Int = addDrink(drinksViewModel, drinkName, price)

            if (error == 0) {
                Toast.makeText(
                    this, "$drinkName angelegt",
                    Toast.LENGTH_SHORT
                ).show()

                tfDrinkName.text.clear()
                tfPrice.text.clear()

                //drinksAdapter.notifyDataSetChanged()
            } else if (error == 1) {
                Toast.makeText(
                    this, "$drinkName ist schon vorhanden",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (error == 2) {
                Toast.makeText(
                    this, "Getränk eingeben",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (error == 3) {
                Toast.makeText(
                    this, "Preis eingeben",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this, "Unknown error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun addDrink(drinksViewModel: DrinksViewModel, drinkName: String, price: Double): Int {
        val drinkID: String = drinkName.lowercase()

        if (!drinkID.equals("") && price > 0.0) {
            // Doppelte checken

            drinksViewModel.addDrink(Drink(drinkName, price))
            return 0
        } else if (drinkName.equals("")) {
            return 2
        } else if (price <= 0) {
            return 3
        } else {
            return 10
        }

    }

    override fun OnItemClick(position: Int, drinks: List<Drink>) {

    }

    override fun onBackPressed() {

    }
}