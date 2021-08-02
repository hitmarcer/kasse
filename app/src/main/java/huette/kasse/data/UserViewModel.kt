package huette.kasse.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val getAllUsers: LiveData<List<User>>
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

    suspend fun getBezahlt(firstName: String, lastName: String): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBezahlt(firstName, lastName)
        }
        return repository.getBezahlt(firstName, lastName)
    }

}