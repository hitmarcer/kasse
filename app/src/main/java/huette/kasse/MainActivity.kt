package huette.kasse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NamesAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val decorView: View = getWindow().decorView

        //enableFullscreen(decorView)

        setContentView(R.layout.activity_main)

        val btnAddUser: Button = findViewById(R.id.btnAddUser)
        val btnDeleteUser: Button = findViewById(R.id.btnDeleteUser)
        val btnEditDrinks: Button = findViewById(R.id.btnEditDrinks)
        val btnPay: Button = findViewById(R.id.btnPay)
        val btnUebersicht: Button = findViewById(R.id.btnUebersicht)
        //val btnFullscreen: Button = findViewById(R.id.btnFullscreen)

        val recyclerViewNames: RecyclerView = findViewById(R.id.recyclerViewNames)

        // Zu Testzwecken
        Variables.addUser("Marc", "Bohner")
        Variables.addUser("Adrian", "Sugg")
        Variables.addUser("Tim", "Disch")
        Variables.addUser("Tobias", "Fink")
        Variables.addUser("Luisa", "Gapp")
        Variables.addUser("Ramona", "Kessler")
        Variables.addUser("Niko", "Hahn")
        Variables.addUser("Julia", "Gapp")
        Variables.addDrink("Bier", 1.5)
        Variables.addDrink("Shot", 1.0)

        val namesAdapter = NamesAdapter(this, Variables.alUserOlds, this)

        recyclerViewNames.adapter = namesAdapter
        recyclerViewNames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        namesAdapter.notifyDataSetChanged()

        btnAddUser.setOnClickListener() {
            startActivity(Intent(this, AddUser::class.java))
        }

        btnDeleteUser.setOnClickListener() {
            Toast.makeText(this, "Wird noch nicht unterstützt", Toast.LENGTH_SHORT).show()
        //startActivity(Intent(this, DeleteUser::class.java))
        }

        btnEditDrinks.setOnClickListener() {
            Variables.function = 3
            startActivity(Intent(this, Password::class.java))
        }

        btnPay.setOnClickListener() {
            Variables.function = 7
            startActivity(Intent(this, Password::class.java))
        }

        btnUebersicht.setOnClickListener() {
            startActivity(Intent(this, Uebersicht::class.java))
        }

    }

    override fun OnItemClick(position: Int) {
        Variables.position = position
        startActivity(Intent(this, AddDrinkToUser::class.java))
    }

    override fun onBackPressed() {

    }

    /*fun buttonClicked(btn: Button) {
        // Funktionen:
        // 1 	= Getränk zu Name hinzufügen, also Szene addDrinkToUserScene mit diesem
        // Benutzer aufrufen
        // 2 	= Benutzer löschen
        // 3 	= Angebot verwalten
        // 4 	= Drink zu Nutzer hinzufügen
        // 5 	= Drink löschen (Angebotsverwaltung)
        // 6 	= Drink von Benutzer wieder entfernen
        // 7 	= Szene bezahlen
        // 8 	= Wirklich bezahlen
        // 9 	= Benutzer wirklich löschen bestätigt

        val function: Int = Variables.function

        // Getränk zu Name hinzufügen, also Szene addDrinkToUser mit diesem
        // Benutzer aufrufen
        if (function == 1) {
            Variables.activeUser = btn.getTag().toString()
            startActivity(Intent(this, AddDrinkToUser::class.java))

        // 2 	= Benutzer löschen
        } else if(function == 2){
            Variables.activeUser = btn.getTag().toString()
            startActivity(Intent(this, DeleteUser::class.java))

        // 3 	= Angebot verwalten
        } else if(function == 3){


        // 4 	= Drink zu Nutzer hinzufügen
        } else if(function == 4){


        // 5 	= Drink löschen (Angebotsverwaltung)
        } else if(function == 5){


        // 6 	= Drink von Benutzer wieder entfernen
        } else if(function == 6){


        // 7 	= Szene bezahlen
        } else if(function == 7){


        // 8 	= Wirklich bezahlen
        } else if(function == 8){


        // 9 	= Benutzer wirklich löschen bestätigt
        } else if(function == 9){

        }
    }*/

    /*private fun enableFullscreen(decorView: View) {
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }*/
}
