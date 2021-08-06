package huette.kasse.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.fragments.FragmentHome
import huette.kasse.fragments.FragmentUebersicht
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()/*, NamesAdapter.OnItemClickListener*/ {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentHome = FragmentHome()
        val fragmentOverview = FragmentUebersicht()

        setCurrentFragment(fragmentHome)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> setCurrentFragment(fragmentHome)
                R.id.ic_edit -> goToEditDrinks()
                R.id.ic_pay -> gotToPay()
                R.id.ic_overview -> setCurrentFragment(fragmentOverview)
            }
            true
        }

        /* val btnAddUser: Button = findViewById(R.id.btnAddUser)
         val btnDeleteUser: Button = findViewById(R.id.btnDeleteUser)
         val btnEditDrinks: Button = findViewById(R.id.btnEditDrinks)
         val btnPay: Button = findViewById(R.id.btnPay)
         val btnUebersicht: Button = findViewById(R.id.btnUebersicht)
         //val btnFullscreen: Button = findViewById(R.id.btnFullscreen)

         val recyclerViewNames: RecyclerView = findViewById(R.id.recyclerViewNames)

         // Zu Testzwecken

         val namesAdapter = NamesAdapter(this, this)

         recyclerViewNames.adapter = namesAdapter
         recyclerViewNames.layoutManager = GridLayoutManager(this, Variables.rows, GridLayoutManager.HORIZONTAL, false)
         //recyclerViewNames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

         // UserViewModel
         val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

         userViewModel.getAllUsers.observe(this, { users ->
             namesAdapter.setData(users)
         })

         //namesAdapter.notifyDataSetChanged()

         btnAddUser.setOnClickListener {
             startActivity(Intent(this, AddUser::class.java))
         }

         btnDeleteUser.setOnClickListener {
             Variables.function = 2
             //Toast.makeText(this, "Wird noch nicht unterstützt", Toast.LENGTH_SHORT).show()
             startActivity(Intent(this, Password::class.java))
         }

         btnEditDrinks.setOnClickListener {
             Variables.function = 3
             startActivity(Intent(this, Password::class.java))
         }

         btnPay.setOnClickListener {
             Variables.function = 7
             startActivity(Intent(this, Password::class.java))
         }

         btnUebersicht.setOnClickListener {
             startActivity(Intent(this, Uebersicht::class.java))
         }*/

    }

    private fun goToEditDrinks() {
        Variables.function = 3
        startActivity(Intent(this, Password::class.java))
    }

    private fun gotToPay() {
        Variables.function = 7
        startActivity(Intent(this, Password::class.java))
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flMain, fragment)
            commit()
        }
    }
/*
    override fun OnItemClick(position: Int, users: List<User>) {
        Variables.position = position
        Variables.user = users[position]
        startActivity(Intent(this, AddDrinkToUser::class.java))
    }*/

    override fun onBackPressed() {

    }
}
