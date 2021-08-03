package huette.kasse.data.repositories

import androidx.lifecycle.LiveData
import huette.kasse.data.daos.UserDrinksDao
import huette.kasse.data.entities.User
import huette.kasse.data.entities.UserDrinks

class UserDrinksRepository(private val userDrinksDao: UserDrinksDao) {

    val getAllData: LiveData<List<UserDrinks>> = userDrinksDao.getAllData()

    fun addDrinkToUser(userDrinks: UserDrinks){
        userDrinksDao.addDrinkToUser(userDrinks)
    }

    fun getUnpaid(userid: Int): Double {
        return userDrinksDao.getUnpaid(userid)
    }

    fun setPaid(userid: Int) {
        userDrinksDao.setPaid(userid)
    }

    fun deleteDrinkFromUser(user_id: Int, drink_id: Int, timestamp: Long){
        userDrinksDao.deleteDrinkFromUser(user_id, drink_id, timestamp)
    }
    fun getUsersWithData(): List<UserDrinks> {
        return userDrinksDao.getUsersWithData()
    }


}