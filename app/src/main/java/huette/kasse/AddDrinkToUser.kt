package huette.kasse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddDrinkToUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_drink_to_user)

        val recyclerViewAddDrinkToUser: RecyclerView = findViewById(R.id.recyclerViewAddDrinkToUser)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, Variables.alDrinks)

        recyclerViewAddDrinkToUser.adapter = drinksAdapter
        recyclerViewAddDrinkToUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onBackPressed() {

    }
}