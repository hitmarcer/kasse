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

}