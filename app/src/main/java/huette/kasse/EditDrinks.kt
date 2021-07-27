package huette.kasse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EditDrinks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_drinks)

        val btnAddDrink: Button = findViewById(R.id.btnAddDrink)
        val btnDeleteDrink: Button = findViewById(R.id.btnDeleteDrink)

        val recyclerViewEditDrinks: RecyclerView = findViewById(R.id.recyclerViewEditDrinks)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, Variables.alDrinks)

        recyclerViewEditDrinks.adapter = drinksAdapter
        recyclerViewEditDrinks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        btnAddDrink.setOnClickListener() {
            startActivity(Intent(this, AddDrink::class.java))
        }

        btnDeleteDrink.setOnClickListener() {
            startActivity(Intent(this, DeleteDrink::class.java))
        }
    }
}