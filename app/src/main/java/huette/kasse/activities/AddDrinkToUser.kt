package huette.kasse.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.DrinksAdapter
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.data.AppDatabase
import huette.kasse.data.entities.Drink
import huette.kasse.data.entities.UserDrinks
import huette.kasse.data.viewmodels.DrinksViewModel

class AddDrinkToUser : AppCompatActivity(), DrinksAdapter.OnItemClickListener {
    lateinit var database: AppDatabase
    lateinit var tvAddDrinkToUser: TextView
    lateinit var fullName: String
    var userPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_drink_to_user)

        database = AppDatabase.getDatabase(this)

        val recyclerViewAddDrinkToUser: RecyclerView = findViewById(R.id.recyclerViewAddDrinkToUser)

        val drinksAdapter: DrinksAdapter = DrinksAdapter(this, this)
        //val namesAdapter: NamesAdapter = NamesAdapter(this, this)

        recyclerViewAddDrinkToUser.adapter = drinksAdapter
        recyclerViewAddDrinkToUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // UserViewModel
        /*val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.getAllUsers.observe(this, Observer { users ->
            namesAdapter.setData(users)
        })*/

        // drinkViewModel
        val drinkViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)

        drinkViewModel.getAllDrinks.observe(this, Observer { drinks ->
            drinksAdapter.setData(drinks)
        })

        tvAddDrinkToUser = findViewById(R.id.tvAddDrinkUser)
        fullName = Variables.user.firstName + " " + Variables.user.lastName
        userPosition = Variables.position
        tvAddDrinkToUser.setText("${fullName}\n${database.userDrinksDao().getUnpaid(Variables.user.id)} €")
    }

    override fun OnItemClick(position: Int, drinks: List<Drink>) {
        Variables.position = position
        Variables.drink = drinks.get(position)

        database.userDrinksDao().addDrinkToUser(UserDrinks(Variables.user.id, Variables.drink.id))
        tvAddDrinkToUser.setText("${fullName}\n${database.userDrinksDao().getUnpaid(Variables.user.id)} €")
        //Variables.alUserOlds.get(userPosition).addAmountToDrink(Variables.alDrinkOlds.get(position))
        //tvAddDrinkToUser.setText("${fullName}\n${Variables.alUserOlds.get(userPosition).userAmount}")

        Toast.makeText(this, "${fullName} (${database.userDrinksDao().getUnpaid(Variables.user.id)} €)", Toast.LENGTH_SHORT).show()

    }

    override fun onBackPressed() {

    }

    /*override fun OnItemClick(position: Int, users: List<User>) {
        TODO("Not yet implemented")
    }*/
}