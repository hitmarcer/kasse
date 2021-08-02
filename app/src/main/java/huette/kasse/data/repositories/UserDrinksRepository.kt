package huette.kasse.data.repositories

import androidx.lifecycle.LiveData
import huette.kasse.data.daos.UserDrinksDao
import huette.kasse.data.entities.UserDrinks

class UserDrinksRepository(private val userDrinksDao: UserDrinksDao) {

    val getAllData: LiveData<List<UserDrinks>> = userDrinksDao.getAllData()

    suspend fun addDrinkToUser(userDrinks: UserDrinks){
        userDrinksDao.addDrinkToUser(userDrinks)
    }

}