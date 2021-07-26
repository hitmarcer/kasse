package huette.kasse

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper

class AddUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user)

        val btnAddUser2: Button = findViewById(R.id.btnAddUser2)
        val tfFirstName: EditText = findViewById(R.id.tfFirstName)
        val tfLastName: EditText = findViewById(R.id.tfLastName)


        Toast.makeText(
            applicationContext, "MainActivity().function: ${MainActivity().function}",
            Toast.LENGTH_SHORT
        ).show()

        btnAddUser2.setOnClickListener() {

            Toast.makeText(
                applicationContext, "MainActivity().function: ${MainActivity().function}",
                Toast.LENGTH_SHORT
            ).show()

            if (MainActivity().function == 1) {

                val layout: LinearLayout = findViewById(R.id.linearLayoutNames)
                val firstName: String = tfFirstName.text.toString()
                val lastName: String = tfLastName.text.toString()
                val userID: String = "${firstName}_${lastName}"
                var error: Int = 0

                if (!firstName.equals("") && !lastName.equals("")) {
                    // Prüfen, ob Nutzer schon vorhanden
                    for (i in 0 until MainActivity().alUsers.size) {
                        if (MainActivity().alUsers.get(i).userID.equals(userID)) {
                            error = 1
                            break
                        }
                    }

                    if (error == 0) {
                        val contextWrapper: ContextThemeWrapper =
                            ContextThemeWrapper(baseContext, R.style.buttonNamesStyle)
                        val btnUser: Button = Button(contextWrapper)

                        btnUser.setText("$firstName\n$lastName")
                        btnUser.setTag(userID)
                        MainActivity().alUsers.add(User(firstName, lastName, userID))
                        MainActivity().alBtnUsers.add(btnUser)

                        Toast.makeText(
                            applicationContext, "$firstName $lastName angelegt",
                            Toast.LENGTH_LONG
                        ).show()

                        tfFirstName.text.clear()
                        tfLastName.text.clear()

                        MainActivity().sortLists(layout)

                        btnUser.setOnClickListener() {
                            MainActivity().buttonClicked(btnUser)
                        }
                    } else {
                        Toast.makeText(
                            applicationContext, "Name schon vorhanden",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        applicationContext, "Vorname und Nachname eingeben",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}