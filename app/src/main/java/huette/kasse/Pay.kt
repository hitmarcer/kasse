package huette.kasse

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Pay : AppCompatActivity(), NamesAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pay)

        val recyclerViewPay: RecyclerView = findViewById(R.id.recyclerViewPay)

        val namesAdapter: NamesAdapter = NamesAdapter(this, Variables.alUsers, this)

        recyclerViewPay.adapter = namesAdapter
        recyclerViewPay.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun OnItemClick(position: Int) {
        if (!Variables.hasPayed(position)) {
            Variables.function = 8
            Variables.position = position
            startActivity(Intent(this, Confirm::class.java))
        } else {
            val firstName: String = Variables.alUsers.get(position).firstName
            val lastName: String = Variables.alUsers.get(position).lastName

            Toast.makeText(this, "${firstName} ${lastName} hat schon bezahlt", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {}
}