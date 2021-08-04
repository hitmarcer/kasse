package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.DrinksAdapter
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.data.entities.Drink
import huette.kasse.data.viewmodels.DrinksViewModel

class EditDrinks : AppCompatActivity(), DrinksAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_drinks)

        val btnAddDrink: Button = findViewById(R.id.btnAddDrink)
        val btnDeleteDrink: Button = findViewById(R.id.btnDeleteDrink)

        val recyclerViewEditDrinks: RecyclerView = findViewById(R.id.recyclerViewEditDrinks)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, this)

        recyclerViewEditDrinks.adapter = drinksAdapter
        recyclerViewEditDrinks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val drinksViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)

        drinksViewModel.getAllDrinks.observe(this, Observer { users ->
            drinksAdapter.setData(users)
        })

        btnAddDrink.setOnClickListener() {
            startActivity(Intent(this, AddDrink::class.java))
        }

        btnDeleteDrink.setOnClickListener() {
            //Toast.makeText(this, "Getränk löschen wird noch nicht unterstützt", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DeleteDrink::class.java))
        }
    }

    override fun OnItemClick(position: Int, drinks: List<Drink>) {
        Variables.drink = drinks[position]
        startActivity(Intent(this, EditDrink::class.java))
        //Toast.makeText(this, "Getränke können noch nicht bearbeitet werden", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

    }
}