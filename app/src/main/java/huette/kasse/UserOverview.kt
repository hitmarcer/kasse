package huette.kasse

import huette.kasse.data.entities.Drink

class UserOverview : Comparable<Any?>{
    var user_id: Int = 0
    var firstName: String = ""
    var lastName: String = ""
    var drinks: ArrayList<Drink> = ArrayList<Drink>()
    var sumAmount: Double = 0.0

    constructor(user_id: Int, firstName: String, lastName: String, sum: Double) :
            super() {
        this.user_id = user_id
        this.firstName = firstName
        this.lastName = lastName
        this.drinks = drinks
        this.sumAmount = sum
    }

    override fun compareTo(other: Any?): Int {
        TODO("Not yet implemented")
        return 0
    }

}