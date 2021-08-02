package huette.kasse.data

import androidx.lifecycle.LiveData

class UserDrinksRepository(private val userDrinksDao: UserDrinksDao) {

    val getAllData: LiveData<List<UserDrinks>> = userDrinksDao.getAllData()

    suspend fun addDrinkToUser(userDrinks: UserDrinks){
        userDrinksDao.addDrinkToUser(userDrinks)
    }

}