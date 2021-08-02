package huette.kasse.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.NamesAdapter
import huette.kasse.R
import huette.kasse.data.UserViewModel
import huette.kasse.data.entities.User

class AddUser : AppCompatActivity(), NamesAdapter.OnItemClickListener {
    // UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user)

        val btnAddUser2: Button = findViewById(R.id.btnAddUser2)
        val tfFirstName: EditText = findViewById(R.id.tfFirstName)
        val tfLastName: EditText = findViewById(R.id.tfLastName)

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewAddUser)

        val namesAdapter: NamesAdapter = NamesAdapter(this, this)

        recyclerViewAddUser.adapter = namesAdapter
        recyclerViewAddUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        //userViewModel.addUser(User("Marc", "Bohner"))

        userViewModel.getAllUsers.observe(this, Observer { users ->
            namesAdapter.setData(users)
        })


        btnAddUser2.setOnClickListener() {
            val firstName: String = tfFirstName.text.toString()
            val lastName: String = tfLastName.text.toString()
            val error: Int = addUser(userViewModel, firstName, lastName)

            /*Toast.makeText(
                applicationContext, "MainActivity().function: ${Variables.function}",
                Toast.LENGTH_SHORT
            ).show()*/

            if (error == 0) {
                Toast.makeText(
                    applicationContext, "$firstName $lastName angelegt",
                    Toast.LENGTH_SHORT
                ).show()

                tfFirstName.text.clear()
                tfLastName.text.clear()

                //namesAdapter.notifyDataSetChanged()

                //startActivity(Intent(this, AddUser::class.java))
            } else if (error == 1) {
                Toast.makeText(
                    applicationContext, "Name schon vorhanden",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (error == 2) {
                Toast.makeText(
                    applicationContext, "Vorname und Nachname eingeben",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    applicationContext, "Unknown error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun addUser(userViewModel: UserViewModel, firstName: String, lastName: String): Int {
        val userID: String = "${firstName.lowercase()}_${lastName.lowercase()}"
        var error: Int = 0

        if (!firstName.equals("") && !lastName.equals("")) {
            // Doppelte checken bevor dann eingetragen wird
                /*if(check_doubles(firstName, lastName) {
                    return 1
                } else {*/

            userViewModel.addUser(User(firstName, lastName))
            return 0
            //}
        } else {
            // Vorname oder Nachname nicht gefüllt
            return 2
        }
    }

    override fun OnItemClick(position: Int, users: List<User>) {
        Toast.makeText(this, "Position: ${position} geklickt Add User", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

    }
}