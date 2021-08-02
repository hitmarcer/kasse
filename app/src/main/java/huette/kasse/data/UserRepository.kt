package huette.kasse.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val getAllUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun getBezahlt(firstName: String, lastName: String): Boolean{
        return userDao.getBezahlt(firstName, lastName)
    }
}