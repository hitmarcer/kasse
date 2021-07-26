package huette.kasse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DeleteDrink : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_drink)

        Variables.function = 5
    }
}