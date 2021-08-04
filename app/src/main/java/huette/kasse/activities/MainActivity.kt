package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
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

class MainActivity : AppCompatActivity(), NamesAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val decorView: View = getWindow().decorView

        //enableFullscreen(decorView)

        setContentView(R.layout.activity_main)

        val btnAddUser: Button = findViewById(R.id.btnAddUser)
        val btnDeleteUser: Button = findViewById(R.id.btnDeleteUser)
        val btnEditDrinks: Button = findViewById(R.id.btnEditDrinks)
        val btnPay: Button = findViewById(R.id.btnPay)
        val btnUebersicht: Button = findViewById(R.id.btnUebersicht)
        //val btnFullscreen: Button = findViewById(R.id.btnFullscreen)

        val recyclerViewNames: RecyclerView = findViewById(R.id.recyclerViewNames)

        // Zu Testzwecken

        val namesAdapter = NamesAdapter(this, this)

        recyclerViewNames.adapter = namesAdapter
        recyclerViewNames.layoutManager = GridLayoutManager(this, Variables.rows, GridLayoutManager.HORIZONTAL, false)
        //recyclerViewNames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // UserViewModel
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.getAllUsers.observe(this, Observer { users ->
            namesAdapter.setData(users)
        })

        //namesAdapter.notifyDataSetChanged()

        btnAddUser.setOnClickListener() {
            startActivity(Intent(this, AddUser::class.java))
        }

        btnDeleteUser.setOnClickListener() {
            Toast.makeText(this, "Wird noch nicht unterstützt", Toast.LENGTH_SHORT).show()
        //startActivity(Intent(this, DeleteUser::class.java))
        }

        btnEditDrinks.setOnClickListener() {
            Variables.function = 3
            startActivity(Intent(this, Password::class.java))
        }

        btnPay.setOnClickListener() {
            Variables.function = 7
            startActivity(Intent(this, Password::class.java))
        }

        btnUebersicht.setOnClickListener() {
            startActivity(Intent(this, Uebersicht::class.java))
        }

    }

    override fun OnItemClick(position: Int, users: List<User>) {
        Variables.position = position
        Variables.user = users.get(position)
        startActivity(Intent(this, AddDrinkToUser::class.java))
    }

    override fun onBackPressed() {

    }
}
