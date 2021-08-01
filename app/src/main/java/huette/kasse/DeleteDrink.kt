package huette.kasse

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DeleteDrink : AppCompatActivity(), DrinksAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_drink)

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewDeleteDrink)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, Variables.alDrinks, this)

        recyclerViewAddUser.adapter = drinksAdapter
        recyclerViewAddUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun OnItemClick(position: Int) {
        //Toast.makeText(this, "Getränk löschen wird noch nicht unterstützt", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

    }
}