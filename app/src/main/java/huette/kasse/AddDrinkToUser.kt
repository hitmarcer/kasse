package huette.kasse

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.ParsePosition

class AddDrinkToUser : AppCompatActivity(), DrinksAdapter.OnItemClickListener {
    lateinit var tvAddDrinkToUser: TextView
    lateinit var fullName: String
    var userPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_drink_to_user)

        val recyclerViewAddDrinkToUser: RecyclerView = findViewById(R.id.recyclerViewAddDrinkToUser)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, Variables.alDrinks, this)

        recyclerViewAddDrinkToUser.adapter = drinksAdapter
        recyclerViewAddDrinkToUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        tvAddDrinkToUser = findViewById(R.id.tvAddDrinkUser)
        fullName = Variables.alUsers.get(Variables.position).firstName + " " + Variables.alUsers.get(Variables.position).lastName
        userPosition = Variables.position
        tvAddDrinkToUser.setText("${fullName}\n${Variables.alUsers.get(userPosition).userAmount}")
    }

    override fun OnItemClick(position: Int) {
        Variables.position = position
        Variables.alUsers.get(userPosition).addAmountToDrink(Variables.alDrinks.get(position))
        tvAddDrinkToUser.setText("${fullName}\n${Variables.alUsers.get(userPosition).userAmount}")
    }

    override fun onBackPressed() {

    }
}