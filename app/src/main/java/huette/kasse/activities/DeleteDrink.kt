package huette.kasse.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.DrinksAdapter
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.data.DrinksViewModel
import huette.kasse.data.entities.Drink

class DeleteDrink : AppCompatActivity(), DrinksAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_drink)

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewDeleteDrink)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, this)

        recyclerViewAddUser.adapter = drinksAdapter
        recyclerViewAddUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val drinksViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)

        drinksViewModel.getAllDrinks.observe(this, Observer { users ->
            drinksAdapter.setData(users)
        })
    }

    override fun OnItemClick(position: Int, drinks: List<Drink>) {
        //Toast.makeText(this, "Getränk löschen wird noch nicht unterstützt", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

    }
}