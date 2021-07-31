package huette.kasse

import java.util.*


class User : Comparable<Any?> {
    var firstName = ""
    var lastName = ""
    var userID = ""
    private var alDrinks = ArrayList<Drink>()
    private val alDrinksBackup = ArrayList<Drink>()
    private val alDrinksTemp = ArrayList<Drink>()
    private var bezahlt = true

    constructor() {}
    constructor(firstName: String, lastName: String, userID: String) : super() {
        this.firstName = firstName
        this.lastName = lastName
        this.userID = userID
    }

    fun isBezahlt(): Boolean {
        // Wenn payAmount <= 0 dann false, ansonsten true
        bezahlt = payAmount <= 0

        return bezahlt
    }

    fun setBezahlt(bezahlt: Boolean) {
        this.bezahlt = bezahlt
        if (bezahlt) {
            // ArrayList Backup leeren (da nur der vorige bezahlte Wert gesichert wird)
            alDrinksBackup.clear()

            // Elemente aus Haupt ArrayList in Backup übertragen
            for (i in alDrinks.indices) {
                alDrinksBackup.add(alDrinks[i])
            }

            // Haupt ArrayList zurücksetzen
            alDrinks.clear()
        }
    }

    val fullName: String
        get() = "$firstName $lastName"

    fun addDrink(drink: Drink) {
        alDrinks.add(drink)
        setBezahlt(false)
    }

    fun addAmountToDrink(drink: Drink) {
        var isKnown: Boolean = false
        var posDrinks: Int = 0
        for (i in alDrinks.indices) {
            if (alDrinks.get(i).drinkID.equals(drink.drinkID)) {
                posDrinks = i
                isKnown = true
            }
        }
        if (isKnown) {
            alDrinks.get(posDrinks).addAmount()
        } else {
            val drink: Drink = Drink(drink.drinkName, drink.drinkID, drink.price)
            drink.addAmount()
            addDrink(drink)
        }
        setBezahlt(false)
    }

    fun restore() {
        bezahlt = false
        // Temp leeren
        alDrinksTemp.clear()

        // Temp mit aktuellen Daten füllen
        for (i in alDrinks.indices) {
            alDrinksTemp.add(alDrinks[i])
        }

        // Hauptliste leeren, um gleich zu füllen
        alDrinks.clear()

        // Elemente aus Backup wiederherstellen
        for (i in alDrinksBackup.indices) {
            alDrinks.add(alDrinksBackup[i])
        }

        // Backup leeren
        alDrinksBackup.clear()

        // Aus Temp in Backup eintragen, falls gefettfingert wurde
        for (i in alDrinksTemp.indices) {
            alDrinksBackup.add(alDrinksTemp[i])
        }

        // Temp wieder leeren
        alDrinksTemp.clear()
        println("Größe alDrinks: " + alDrinks.size)
    }

    val payAmount: Double
        get() {
            var payment = 0.0
            for (i in alDrinks.indices) {
                payment += alDrinks[i].price * alDrinks[i].amount
                println(
                    "Name: " + userID + "   Drink: " + alDrinks[i].drinkID
                            + "   Anzahl: " + alDrinks[i].amount + "   Preis: " + alDrinks[i].price + " €"
                )
            }
            return payment
        }

    val userData: String
        get() {
            var message = ""
            message = "$firstName $lastName: "
            return message
        }

    val userAmount: String
        get() {
            var message = ""
            message = "$payAmount €"
            return message
        }

    override fun toString(): String {
        return "[ userID=$userID, First Name=$firstName, Last Name=$lastName]"
    }

    override operator fun compareTo(other: Any?): Int {
        // TODO Auto-generated method stub
        return 0
    }

    /*companion object {
        var userComparator = Comparator<User> { u1, u2 ->
            val user1 = u1.userID.toUpperCase()
            val user2 = u2.userID.toUpperCase()

            // ascending order
            user1.compareTo(user2)

            // descending order
            // return StudentName2.compareTo(StudentName1);
        }
    }*/
}