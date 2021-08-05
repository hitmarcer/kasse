package huette.kasse.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.DrinksAdapter
import huette.kasse.R
import huette.kasse.data.AppDatabase
import huette.kasse.data.entities.Drink
import huette.kasse.data.viewmodels.DrinksViewModel

class AddDrink : AppCompatActivity(), DrinksAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_drink)

        val btnAddDrink2: Button = findViewById(R.id.btnAddDrink2)
        val tfDrinkName: EditText = findViewById(R.id.tfDrinkName)
        val tfPrice: EditText = findViewById(R.id.tfPrice)

        val recyclerViewAddDrink: RecyclerView = findViewById(R.id.recyclerViewAddDrink)

        val drinksAdapter = DrinksAdapter(this, this)

        recyclerViewAddDrink.adapter = drinksAdapter
        recyclerViewAddDrink.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val drinksViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)

        drinksViewModel.getAllDrinks.observe(this, { users ->
            drinksAdapter.setData(users)
        })

        btnAddDrink2.setOnClickListener {
            val drinkName: String = tfDrinkName.text.toString()

            // Programm bricht ab, wenn im Textfeld nichts drin steht und Methode toDouble() ausgeführt wird
            val price: Double = if (tfPrice.text.toString() == "") {
                0.0
            } else {
                tfPrice.text.toString().toDouble()
            }

            when (addDrink(drinksViewModel, drinkName, price)) {
                0 -> {
                    Toast.makeText(
                        this, "$drinkName angelegt",
                        Toast.LENGTH_SHORT
                    ).show()

                    tfDrinkName.text.clear()
                    tfPrice.text.clear()

                    //drinksAdapter.notifyDataSetChanged()
                }
                1 -> {
                    Toast.makeText(
                        this, "$drinkName ist schon vorhanden",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                2 -> {
                    Toast.makeText(
                        this, "Getränk eingeben",
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

    fun addDrink(drinksViewModel: DrinksViewModel, drinkName: String, price: Double): Int {

        if (drinkName != "" && price > 0.0) {
            // Doppelte checken
            val drink = AppDatabase.getDatabase(application).drinkDao().getDrinkByName(drinkName)

            if(drink != null){
                if(drinkName == drink.drinkName){
                    return 1
                }
            }

            drinksViewModel.addDrink(Drink(drinkName, price))
            return 0
        } else if (drinkName == "") {
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