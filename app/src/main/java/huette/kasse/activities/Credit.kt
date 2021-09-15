package huette.kasse.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
import kotlinx.android.synthetic.main.add_drink.*

class Credit : AppCompatActivity() {
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credit)

        val btnAddCredit: Button = findViewById(R.id.btnAddCredit2)
        val tfCredit: EditText = findViewById(R.id.tfCredit)
        val tvCredit: TextView = findViewById(R.id.tvCredit)

        database = AppDatabase.getDatabase(application)

        val userID = Variables.user.id
        val oldCredit: Double = database.userDao().getCredit(userID)

        tvCredit.text = "Guthaben: " + String.format("%.2f", oldCredit) + " €"

        btnAddCredit.setOnClickListener {
            var credit: Double = 0.0

            if (!tfCredit.text.toString().equals("")){
                credit= tfCredit.text.toString().toDouble()
            }

            if (credit > 0.0) {
                val newCreditString: String = String.format("%.2f", addCredit(credit))
                tvCredit.text = "Guthaben: $newCreditString €"
                tfCredit.text.clear()
            } else {
                Toast.makeText(this, "Falsche Eingabe", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Returns new credit
    fun addCredit(credit: Double): Double {
        val userID: Int = Variables.user.id
        val creditString: String = String.format("%.2f", credit)
        val oldCredit: Double = database.userDao().getCredit(userID)
        val newCredit: Double = calcCredit(userID, credit)

        // Guthaben hinzufügen
        database.userDao().setCredit(userID, newCredit)

        Toast.makeText(this, "$creditString € hinzugefügt", Toast.LENGTH_SHORT).show()

        return newCredit
    }

    fun calcCredit(userID: Int, addedCredit: Double): Double {
        val oldCredit: Double = database.userDao().getCredit(userID)
        val newCredit = oldCredit + addedCredit

        return newCredit
    }
}