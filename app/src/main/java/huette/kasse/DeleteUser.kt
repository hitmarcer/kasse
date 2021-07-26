package huette.kasse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DeleteUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_user)

        Variables.function = 2
    }
}