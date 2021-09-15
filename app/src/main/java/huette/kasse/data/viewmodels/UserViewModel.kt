package huette.kasse.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import huette.kasse.data.AppDatabase
import huette.kasse.data.entities.User
import huette.kasse.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val getAllUsers: LiveData<List<User>>
    private val repository: UserRepository

    init{
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllUsers = repository.getAllUsers
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun getAllUsers(): LiveData<List<User>>{
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUsers
        }
        return repository.getAllUsers
    }

    fun getSingleUser(user_id: Int): User {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSingleUser(user_id)
        }
        return repository.getSingleUser(user_id)
    }

    fun getAllUsersList(): List<User> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUsersList()
        }
        return repository.getAllUsersList()
    }

    fun getUserByName(firstname: String, lastname: String): User {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserByName(firstname, lastname)
        }
        return  repository.getUserByName(firstname, lastname)
    }

    fun reactivateUser(user_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.reactivateUser(user_id)
        }
    }

    fun setCredit(user_id: Int, credit: Double){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setCredit(user_id, credit)
        }
    }

    fun getCredit(user_id: Int): Double {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCredit(user_id)
        }
        return repository.getCredit(user_id)
    }

}