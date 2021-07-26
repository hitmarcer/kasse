package huette.kasse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Confirm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirm)

        // Nur auf 8 setzen, wenn man von Szene/Activity bezahlen kommt
        Variables.function = 8
    }
}