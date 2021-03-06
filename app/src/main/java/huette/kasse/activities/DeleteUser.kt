package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
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

class DeleteUser : AppCompatActivity(), NamesAdapter.OnItemClickListener {
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_user)

        Variables.function = 2

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewDeleteUser)
        val searchView: SearchView = findViewById(R.id.searchViewDelete)

        val namesAdapter = NamesAdapter(this, this)

        database = AppDatabase.getDatabase(application)

        recyclerViewAddUser.adapter = namesAdapter
        recyclerViewAddUser.layoutManager =
            GridLayoutManager(this, Variables.cols)

        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        //userViewModel.addUser(User("Marc", "Bohner"))

        userViewModel.getAllUsers.observe(this, { users ->
            namesAdapter.setData(users)
        })

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                namesAdapter.filter.filter(newText)
                return true
            }
        })
    }

    override fun OnItemClick(position: Int, users: List<User>) {
        val user_id = users[position].id
        val firstName = users[position].firstName
        val lastName = users[position].lastName

        val offenerBetrag = database.userDrinksDao().getUnpaid(user_id)

        if (offenerBetrag > 0) {
            Toast.makeText(
                this,
                "$firstName $lastName kann nicht gel??scht werden, da noch nicht bezahlt wurde",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Variables.user = users[position]
            Variables.function = 2
            startActivity(Intent(this, Confirm::class.java))
        }
    }

    override fun onBackPressed() {

    }
}