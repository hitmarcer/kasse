package huette.kasse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Pay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pay)

        Variables.function = 7
    }
}