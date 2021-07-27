package huette.kasse

class Drink {

    var drinkName = ""
    var drinkID = ""
    var price = 0.0
    var amount = 0

    constructor() {}
    constructor(drinkName: String, drinkID: String, price: Double) : super() {
        this.drinkName = drinkName
        this.drinkID = drinkID
        this.price = price
    }

    fun addAmount() {
        amount += 1
        println("1 Added to amount. New Amount: $amount ($price €")
    }
}