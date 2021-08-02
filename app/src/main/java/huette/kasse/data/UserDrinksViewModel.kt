package huette.kasse.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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

}