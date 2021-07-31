package huette.kasse

import android.widget.Button

class Variables {
    companion object {
        var alBtnDrinks: ArrayList<Button> = ArrayList<Button>()
        var alUsers: ArrayList<User> = ArrayList<User>()
        var alDrinks: ArrayList<Drink> = ArrayList<Drink>()
        var position: Int = 0

        val pw = ""

        var function: Int = 0
        var activeUser: String = ""

        fun sortLists() {
            alUsers.sortWith(compareBy({ it.userID }))
            alDrinks.sortWith(compareBy({ it.drinkID }))
            /*alBtnUsers = alBtnUsers.sortedWith(compareBy({ it.tag.toString() })) as ArrayList<Button>*/
        }

        fun addUser(firstName: String, lastName: String): Int {
            val userID: String = "${firstName.lowercase()}_${lastName.lowercase()}"
            var error: Int = 0

            if (!firstName.equals("") && !lastName.equals("")) {
                for (i in 0 until alUsers.size) {
                    if (alUsers.get(i).userID.equals(userID)) {
                        return 1
                    }
                }

                alUsers.add(User(firstName, lastName, userID))
                sortLists()

                return 0
            } else {
                // Vorname oder Nachname nicht gefüllt
                return 2
            }
        }

        fun addDrink(drinkName: String, price: Double): Int {
            val drinkID: String = drinkName.lowercase()

            if (!drinkName.equals("") && price >= 0.0) {
                for (i in 0 until alDrinks.size) {
                    if (drinkID.equals(alDrinks.get(i).drinkID)) {
                        return 1
                    }
                }
                alDrinks.add(Drink(drinkName, drinkID, price))

                return 0
            } else if (drinkName.equals("")) {
                return 2
            } else if (price <= 0) {
                return 3
            } else {
                return 10
            }

        }

        fun hasPayed(position: Int): Boolean {
            return alUsers.get(position).isBezahlt()
        }
    }
}