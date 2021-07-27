package huette.kasse

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user)

        val btnAddUser2: Button = findViewById(R.id.btnAddUser2)
        val tfFirstName: EditText = findViewById(R.id.tfFirstName)
        val tfLastName: EditText = findViewById(R.id.tfLastName)

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewAddUser)

        val namesAdapter: NamesAdapter = NamesAdapter(this, Variables.alUsers)

        recyclerViewAddUser.adapter = namesAdapter
        recyclerViewAddUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        /*val layoutNames: LinearLayout = findViewById(R.id.linearLayoutNames)
        layoutNames.removeAllViews()
        for (i in 0 until Variables.alBtnUsers.size){
            layoutNames.addView(Variables.alBtnUsers.get(i))
        }*/

        Variables.function = 1

        btnAddUser2.setOnClickListener() {

            /*Toast.makeText(
                applicationContext, "MainActivity().function: ${Variables.function}",
                Toast.LENGTH_SHORT
            ).show()*/

            if (Variables.function == 1) {

                //val layout: LinearLayout = findViewById(R.id.linearLayoutNames)
                val firstName: String = tfFirstName.text.toString()
                val lastName: String = tfLastName.text.toString()
                val error: Int = Variables.addUser(firstName, lastName)

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
    }
}