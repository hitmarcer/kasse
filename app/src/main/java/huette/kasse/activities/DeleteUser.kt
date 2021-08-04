package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.NamesAdapter
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.data.entities.User
import huette.kasse.data.viewmodels.UserViewModel

class DeleteUser : AppCompatActivity(), NamesAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_user)

        Variables.function = 2

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewDeleteUser)

        val namesAdapter: NamesAdapter = NamesAdapter(this, this)

        recyclerViewAddUser.adapter = namesAdapter
        recyclerViewAddUser.layoutManager =
            GridLayoutManager(this, Variables.rows, GridLayoutManager.HORIZONTAL, false)

        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        //userViewModel.addUser(User("Marc", "Bohner"))

        userViewModel.getAllUsers.observe(this, Observer { users ->
            namesAdapter.setData(users)
        })
    }

    override fun OnItemClick(position: Int, users: List<User>) {
        Variables.position = position
        startActivity(Intent(this, Confirm::class.java))
    }

    override fun onBackPressed() {

    }
}