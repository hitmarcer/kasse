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
}