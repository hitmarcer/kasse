package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import huette.kasse.R
import huette.kasse.Variables

class Password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password)

        val btnConfirm: Button = findViewById(R.id.btnConfirm)
        val tfPassword: EditText = findViewById(R.id.tfPassword)

        btnConfirm.setOnClickListener() {
            val password = tfPassword.text.toString()
            if (password.equals(Variables.pw)) {
                // Name löschen aufrufen
                if (Variables.function == 2) {
                    startActivity(Intent(this, DeleteUser::class.java))
                // Angebot verwalten aufrufen
                }else if(Variables.function == 3){
                    startActivity(Intent(this, EditDrinks::class.java))
                // Bezahlen aufrufen
                } else if (Variables.function == 7){
                    startActivity(Intent(this, Pay::class.java))
                }
            } else {
                Toast.makeText(
                    applicationContext, "Passwort falsch",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onBackPressed() {

    }
}