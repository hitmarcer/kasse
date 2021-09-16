package huette.kasse.data.repositories

import androidx.lifecycle.LiveData
import huette.kasse.data.daos.UserDao
import huette.kasse.data.entities.User

class UserRepository(private val userDao: UserDao) {

    val getAllUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    fun getSingleUser(user_id: Int): User {
        return userDao.getSingleUser(user_id)
    }

    fun getAllUsersList(): List<User> {
        return userDao.getAllUsersList()
    }

    fun getUserByName(firstname: String, lastname: String): User {
        return userDao.getUserByName(firstname, lastname)
    }
    fun reactivateUser(user_id: Int) {
        userDao.reactivateUser(user_id)
    }

    fun setCredit(user_id: Int, credit: Double){
        userDao.setCredit(user_id, credit)
    }

    fun getCredit(user_id: Int): Double{
        return userDao.getCredit(user_id)
    }

    fun setUnpaidAmount(user_id: Int, unpaidAmount: Double){
        userDao.setUnpaidAmount(user_id, unpaidAmount)
    }

    fun getUnpaidAmount(user_id: Int): Double{
        return userDao.getUnpaidAmount(user_id)
    }

}