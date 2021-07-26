package huette.kasse

import android.widget.Button
import android.widget.LinearLayout

class Variables {
    companion object{
        fun sortLists(layout: LinearLayout) {
            alUsers.sortWith(compareBy({ it.userID }))
            alBtnUsers.sortWith(compareBy({ it.tag.toString() }))
            /*alBtnUsers = alBtnUsers.sortedWith(compareBy({ it.tag.toString() })) as ArrayList<Button>*/

            layout.removeAllViews()

            for (i in 0 until alBtnUsers.size) {
                layout.addView(alBtnUsers.get(i))
            }
        }

        var alBtnUsers: ArrayList<Button> = ArrayList<Button>()
        var alBtnDrinks: ArrayList<Button> = ArrayList<Button>()
        var alUsers: ArrayList<User> = ArrayList<User>()
        var alDrinks: ArrayList<Drink> = ArrayList<Drink>()

        val pw = ""

        var function: Int = 0
        var activeUser: String = ""
    }
}