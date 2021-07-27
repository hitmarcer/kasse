package huette.kasse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password)

        val btnConfirm: Button = findViewById(R.id.btnConfirm)
        val tfPassword: EditText = findViewById(R.id.tfPassword)

        btnConfirm.setOnClickListener() {
            val password = tfPassword.text.toString()
            if (password.equals(Variables.pw)) {
                if(Variables.function == 3){
                    startActivity(Intent(this, EditDrinks::class.java))
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
}