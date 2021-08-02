package huette.kasse.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.DrinksAdapter
import huette.kasse.NamesAdapter
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.data.DrinksViewModel
import huette.kasse.data.UserViewModel

class AddDrinkToUser : AppCompatActivity(), DrinksAdapter.OnItemClickListener,
    NamesAdapter.OnItemClickListener {
    lateinit var tvAddDrinkToUser: TextView
    lateinit var fullName: String
    var userPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_drink_to_user)

        val recyclerViewAddDrinkToUser: RecyclerView = findViewById(R.id.recyclerViewAddDrinkToUser)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, this)
        val namesAdapter: NamesAdapter = NamesAdapter(this, this)

        recyclerViewAddDrinkToUser.adapter = drinksAdapter
        recyclerViewAddDrinkToUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // UserViewModel
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.getAllUsers.observe(this, Observer { users ->
            namesAdapter.setData(users)
        })

        // drinkViewModel
        val drinkViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)

        drinkViewModel.getAllDrinks.observe(this, Observer { drinks ->
            drinksAdapter.setData(drinks)
        })

        tvAddDrinkToUser = findViewById(R.id.tvAddDrinkUser)
        fullName = Variables.alUserOlds.get(Variables.position).firstName + " " + Variables.alUserOlds.get(
            Variables.position
        ).lastName
        userPosition = Variables.position
        tvAddDrinkToUser.setText("${fullName}\n${Variables.alUserOlds.get(userPosition).userAmount}")
    }

    override fun OnItemClick(position: Int) {
        Variables.position = position
        Variables.alUserOlds.get(userPosition).addAmountToDrink(Variables.alDrinkOlds.get(position))
        tvAddDrinkToUser.setText("${fullName}\n${Variables.alUserOlds.get(userPosition).userAmount}")
    }

    override fun onBackPressed() {

    }
}