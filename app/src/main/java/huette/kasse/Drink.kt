package huette.kasse

class Drink {

    private var name = ""
    private var drinkID = ""
    private var price = 0.0
    private var amount = 0

    fun Drink() {}

    fun Drink(name: String, price: Double, drinkID: String) {
        this.name = name
        this.price = price
        this.drinkID = drinkID
        amount = 1
    }

    fun addAmount() {
        amount += 1
        println("1 Added to amount. New Amount: $amount ($price €")
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getPrice(): Double {
        return price
    }

    fun setPrice(price: Double) {
        this.price = price
    }

    fun getAmount(): Int {
        return amount
    }

    fun setAmount(amount: Int) {
        this.amount = amount
    }

    fun getDrinkID(): String? {
        return drinkID
    }

    fun setDrinkID(drinkID: String) {
        this.drinkID = drinkID
    }
}