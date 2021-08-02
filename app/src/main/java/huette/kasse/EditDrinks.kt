package huette.kasse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EditDrinks : AppCompatActivity(), DrinksAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_drinks)

        val btnAddDrink: Button = findViewById(R.id.btnAddDrink)
        val btnDeleteDrink: Button = findViewById(R.id.btnDeleteDrink)

        val recyclerViewEditDrinks: RecyclerView = findViewById(R.id.recyclerViewEditDrinks)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, Variables.alDrinkOlds, this)

        recyclerViewEditDrinks.adapter = drinksAdapter
        recyclerViewEditDrinks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        btnAddDrink.setOnClickListener() {
            startActivity(Intent(this, AddDrink::class.java))
        }

        btnDeleteDrink.setOnClickListener() {
            Toast.makeText(this, "Getränk löschen wird noch nicht unterstützt", Toast.LENGTH_SHORT).show()
            //startActivity(Intent(this, DeleteDrink::class.java))
        }
    }

    override fun OnItemClick(position: Int) {
        Toast.makeText(this, "Getränke können noch nicht bearbeitet werden", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

    }
}