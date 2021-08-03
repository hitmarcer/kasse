package huette.kasse.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import huette.kasse.data.AppDatabase
import huette.kasse.data.entities.UserDrinks
import huette.kasse.data.repositories.UserDrinksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDrinksViewModel(application: Application): AndroidViewModel(application) {

    private val getAllData: LiveData<List<UserDrinks>>
    private val repository: UserDrinksRepository

    init{
        val userDrinksDao = AppDatabase.getDatabase(application).userDrinksDao()
        repository = UserDrinksRepository(userDrinksDao)
        getAllData = repository.getAllData
    }

    fun addDrinkToUser(userDrink: UserDrinks){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDrinkToUser(userDrink)
        }
    }

    fun getUnpaid(userid: Int): Double {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnpaid(userid)
        }
        return repository.getUnpaid(userid)
    }

    fun setPaid(userid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setPaid(userid)
        }
    }

}