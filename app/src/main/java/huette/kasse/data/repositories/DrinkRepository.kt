package huette.kasse.data.repositories

import androidx.lifecycle.LiveData
import huette.kasse.data.daos.DrinkDao
import huette.kasse.data.entities.Drink

class DrinkRepository(private val drinkDao: DrinkDao) {

    val getAllDrinks: LiveData<List<Drink>> = drinkDao.getAllDrinks()
// Test123
    fun addDrink(drink: Drink){
        drinkDao.addDrink(drink)
    }

}