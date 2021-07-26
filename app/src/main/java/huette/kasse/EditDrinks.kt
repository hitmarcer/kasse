package huette.kasse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class EditDrinks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_drinks)

        MainActivity().function = 3
    }
}