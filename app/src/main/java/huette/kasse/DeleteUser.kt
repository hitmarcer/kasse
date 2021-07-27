package huette.kasse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DeleteUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_user)

        Variables.function = 2

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewDeleteUser)

        val namesAdapter: NamesAdapter = NamesAdapter(this, Variables.alUsers)

        recyclerViewAddUser.adapter = namesAdapter
        recyclerViewAddUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}