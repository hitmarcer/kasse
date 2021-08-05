package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.NamesAdapter
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.data.AppDatabase
import huette.kasse.data.entities.User
import huette.kasse.data.viewmodels.UserViewModel

class Pay : AppCompatActivity(), NamesAdapter.OnItemClickListener {
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pay)

        database = AppDatabase.getDatabase(application)

        val recyclerViewPay: RecyclerView = findViewById(R.id.recyclerViewPay)

        val namesAdapter = NamesAdapter(this, this)

        recyclerViewPay.adapter = namesAdapter
        recyclerViewPay.layoutManager =
            GridLayoutManager(this, Variables.rows, GridLayoutManager.HORIZONTAL, false)

        // UserViewModel
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.getAllUsers.observe(this, { users ->
            namesAdapter.setData(users)
        })
    }

    override fun OnItemClick(position: Int, users: List<User>) {
        if (!everythingPaid(users[position])){
            Variables.user = users[position]
            Variables.function = 8
            startActivity(Intent(this, Confirm::class.java))
        } else {
            val firstName: String = users[position].firstName
            val lastName: String = users[position].lastName

            Toast.makeText(this, "$firstName $lastName hat schon bezahlt", Toast.LENGTH_SHORT).show()
        }

    /*if (!Variables.hasPayed(position)) {
            Variables.function = 8
            Variables.position = position
            startActivity(Intent(this, Confirm::class.java))
        } else {
            val firstName: String = Variables.alUserOlds.get(position).firstName
            val lastName: String = Variables.alUserOlds.get(position).lastName

            Toast.makeText(this, "${firstName} ${lastName} hat schon bezahlt", Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun everythingPaid(user: User): Boolean {
        return database.userDrinksDao().getUnpaid(user.id) <= 0
    }

    override fun onBackPressed() {}
}