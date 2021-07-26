package huette.kasse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper

class MainActivity : AppCompatActivity() {

    var alBtnUsers: ArrayList<Button> = ArrayList<Button>()
    var alBtnDrinks: ArrayList<Button> = ArrayList<Button>()
    var alUsers: ArrayList<User> = ArrayList<User>()
    var alDrinks: ArrayList<Drink> = ArrayList<Drink>()
    //Test

    val pw = ""

    var function: Int = 0
    var activeUser: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val decorView: View = getWindow().decorView

        //enableFullscreen(decorView)

        setContentView(R.layout.activity_main)

        val btnAddUser: Button = findViewById(R.id.btnAddUser)
        val btnDeleteUser: Button = findViewById(R.id.btnDeleteUser)
        val btnEditDrinks: Button = findViewById(R.id.btnEditDrinks)
        val btnPay: Button = findViewById(R.id.btnPay)
        val btnUebersicht: Button = findViewById(R.id.btnUebersicht)
        val btnFullscreen: Button = findViewById(R.id.btnFullscreen)

        btnFullscreen.setOnClickListener() {
            enableFullscreen(decorView)
        }

        btnAddUser.setOnClickListener() {
            val intent: Intent = Intent(this, AddUser::class.java)
            startActivity(Intent(this, AddUser::class.java))
        }

        btnDeleteUser.setOnClickListener() {
            startActivity(Intent(this, DeleteUser::class.java))
        }

        btnEditDrinks.setOnClickListener() {
            function = 3
            startActivity(Intent(this, Password::class.java))
        }

        btnPay.setOnClickListener() {
            function = 7
            startActivity(Intent(this, Password::class.java))
        }

        btnUebersicht.setOnClickListener() {
            startActivity(Intent(this, Uebersicht::class.java))
        }

    }

    fun buttonClicked(btn: Button) {
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

        val function: Int = function

        // Getränk zu Name hinzufügen, also Szene addDrinkToUser mit diesem
        // Benutzer aufrufen
        if (function == 1) {
            activeUser = btn.getTag().toString()
            startActivity(Intent(this, AddDrinkToUser::class.java))

        // 2 	= Benutzer löschen
        } else if(function == 2){
            activeUser = btn.getTag().toString()
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
    }

    fun sortLists(layout: LinearLayout) {
        alUsers.sortWith(compareBy({ it.userID }))
        alBtnUsers.sortWith(compareBy({ it.tag.toString() }))
        /*alBtnUsers = alBtnUsers.sortedWith(compareBy({ it.tag.toString() })) as ArrayList<Button>*/

        layout.removeAllViews()

        for (i in 0 until alBtnUsers.size) {
            layout.addView(alBtnUsers.get(i))
        }
    }

    private fun enableFullscreen(decorView: View) {
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun addButton(firstName: String, lastName: String) {
        val context: ContextThemeWrapper =
            ContextThemeWrapper(baseContext, R.style.buttonNamesStyle)
        val layout: LinearLayout = findViewById(R.id.linearLayoutNames)
        val button: Button = Button(context)
        button.setText("${firstName}\n${lastName}")

        layout.addView(button)
    }
}
