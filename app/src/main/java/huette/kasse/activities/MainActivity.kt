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

    override fun onBackPressed() {

    }
}
