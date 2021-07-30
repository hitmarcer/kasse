package huette.kasse

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DeleteUser : AppCompatActivity(), NamesAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_user)

        Variables.function = 2

        val recyclerViewAddUser: RecyclerView = findViewById(R.id.recyclerViewDeleteUser)

        val namesAdapter: NamesAdapter = NamesAdapter(this, Variables.alUsers, this)

        recyclerViewAddUser.adapter = namesAdapter
        recyclerViewAddUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun OnItemClick(position: Int) {
        Toast.makeText(this, "Position: ${position} geklickt Delete User", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

    }
}