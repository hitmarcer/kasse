package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.DrinksAdapter
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.data.entities.Drink
import huette.kasse.data.viewmodels.DrinksViewModel

class DeleteDrink : AppCompatActivity(), DrinksAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_drink)

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewDeleteDrink)

        val drinksAdapter = DrinksAdapter(this, this)

        recyclerViewAddUser.adapter = drinksAdapter
        recyclerViewAddUser.layoutManager =
            GridLayoutManager(this, Variables.cols)

        val drinksViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)

        drinksViewModel.getAllDrinks.observe(this, { users ->
            drinksAdapter.setData(users)
        })
    }

    override fun OnItemClick(position: Int, drinks: List<Drink>) {
        Variables.function = 5
        Variables.drink = drinks[position]
        startActivity(Intent(this, Confirm::class.java))
        //Toast.makeText(this, "Getränk löschen wird noch nicht unterstützt", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

    }
}