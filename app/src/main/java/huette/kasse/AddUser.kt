package huette.kasse

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddUser : AppCompatActivity(), NamesAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user)

        val btnAddUser2: Button = findViewById(R.id.btnAddUser2)
        val tfFirstName: EditText = findViewById(R.id.tfFirstName)
        val tfLastName: EditText = findViewById(R.id.tfLastName)

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewAddUser)

        val namesAdapter: NamesAdapter = NamesAdapter(this, Variables.alUsers, this)

        recyclerViewAddUser.adapter = namesAdapter
        recyclerViewAddUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        btnAddUser2.setOnClickListener() {
            val firstName: String = tfFirstName.text.toString()
            val lastName: String = tfLastName.text.toString()
            val error: Int = Variables.addUser(firstName, lastName)

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

                namesAdapter.notifyDataSetChanged()

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

    override fun OnItemClick(position: Int) {
        Toast.makeText(this, "Position: ${position} geklickt Add User", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

    }
}