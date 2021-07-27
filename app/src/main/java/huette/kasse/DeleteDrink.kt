package huette.kasse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DeleteDrink : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_drink)

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewDeleteDrink)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, Variables.alDrinks)

        recyclerViewAddUser.adapter = drinksAdapter
        recyclerViewAddUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}