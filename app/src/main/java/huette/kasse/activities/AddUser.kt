package huette.kasse.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.NamesAdapter
import huette.kasse.R
import huette.kasse.data.AppDatabase
import huette.kasse.data.entities.User
import huette.kasse.data.viewmodels.UserViewModel

class AddUser : AppCompatActivity(), NamesAdapter.OnItemClickListener {
    // UserViewModel
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user)

        val btnAddUser2: Button = findViewById(R.id.btnAddUser2)
        val tfFirstName: EditText = findViewById(R.id.tfFirstName)
        val tfLastName: EditText = findViewById(R.id.tfLastName)

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewAddUser)

        val namesAdapter = NamesAdapter(this, this)

        database = AppDatabase.getDatabase(application)

        recyclerViewAddUser.adapter = namesAdapter
        recyclerViewAddUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        //userViewModel.addUser(User("Marc", "Bohner"))

        userViewModel.getAllUsers.observe(this, { users ->
            namesAdapter.setData(users)
        })


        btnAddUser2.setOnClickListener {
            val firstName: String = tfFirstName.text.toString()
            val lastName: String = tfLastName.text.toString()

            when (addUser(userViewModel, firstName, lastName)) {
                0 -> {
                    Toast.makeText(
                        applicationContext, "$firstName $lastName angelegt",
                        Toast.LENGTH_SHORT
                    ).show()

                    tfFirstName.text.clear()
                    tfLastName.text.clear()

                    //namesAdapter.notifyDataSetChanged()

                    //startActivity(Intent(this, AddUser::class.java))
                }
                1 -> {
                    Toast.makeText(
                        applicationContext, "Name schon vorhanden",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                2 -> {
                    Toast.makeText(
                        applicationContext, "Vorname und Nachname eingeben",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        applicationContext, "Unknown error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun addUser(userViewModel: UserViewModel, firstName: String, lastName: String): Int {
        "${firstName.lowercase()}_${lastName.lowercase()}"

        if (firstName != "" && lastName != "") {
            // Doppelte checken
            val user = database.userDao().getUserByName(firstName, lastName)

            if (user != null && !user.deleted) {
                if (firstName == user.firstName && lastName == (user.lastName)) {
                    return 1
                }
            } else if (user != null && user.deleted) {
                userViewModel.reactivateUser(user.id)
                return 0
            }
            userViewModel.addUser(User(firstName, lastName))
            return 0
        } else {
            // Vorname oder Nachname nicht gef??llt
            return 2
        }
    }

    override fun OnItemClick(position: Int, users: List<User>) {
        Toast.makeText(this, "Position: $position geklickt Add User", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

    }
}