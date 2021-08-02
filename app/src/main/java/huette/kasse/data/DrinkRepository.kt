package huette.kasse.data

import androidx.lifecycle.LiveData

class DrinkRepository(private val drinkDao: DrinkDao) {

    val getAllDrinks: LiveData<List<Drink>> = drinkDao.getAllDrinks()

    suspend fun addDrink(drink: Drink){
        drinkDao.addDrink(drink)
    }

}