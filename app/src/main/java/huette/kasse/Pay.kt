package huette.kasse

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
        Toast.makeText(this, "Position: ${position} geklickt Pay", Toast.LENGTH_SHORT).show()
    }
}