package huette.kasse

import android.widget.Button
import huette.kasse.data.AppDatabase

class Variables {
    companion object {
        var alBtnDrinks: ArrayList<Button> = ArrayList<Button>()
        var alUserOlds: ArrayList<UserOld> = ArrayList<UserOld>()
        var alDrinkOlds: ArrayList<DrinkOld> = ArrayList<DrinkOld>()
        lateinit var userDatabase: AppDatabase
        var position: Int = 0

        val pw = ""

        var function: Int = 0
        var activeUser: String = ""

        fun sortLists() {
            alUserOlds.sortWith(compareBy({ it.userID }))
            alDrinkOlds.sortWith(compareBy({ it.drinkID }))
            /*alBtnUsers = alBtnUsers.sortedWith(compareBy({ it.tag.toString() })) as ArrayList<Button>*/
        }

        fun addUser(firstName: String, lastName: String): Int {
            val userID: String = "${firstName.lowercase()}_${lastName.lowercase()}"
            var error: Int = 0

            if (!firstName.equals("") && !lastName.equals("")) {
                for (i in 0 until alUserOlds.size) {
                    if (alUserOlds.get(i).userID.equals(userID)) {
                        return 1
                    }
                }

                alUserOlds.add(UserOld(firstName, lastName, userID))
                //userDatabase.userDao().addUser(User(firstName = firstName, lastName = lastName))
                //test
                sortLists()

                return 0
            } else {
                // Vorname oder Nachname nicht gefüllt
                return 2
            }
        }

        fun addDrink(drinkName: String, price: Double): Int {
            val drinkID: String = drinkName.lowercase()

            if (!drinkID.equals("") && price > 0.0) {
                for (i in 0 until alDrinkOlds.size) {
                    if (drinkID.equals(alDrinkOlds.get(i).drinkID)) {
                        return 1
                    }
                }
                alDrinkOlds.add(DrinkOld(drinkName, drinkID, price))

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
            return alUserOlds.get(position).isBezahlt()
        }
    }
}