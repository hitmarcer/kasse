package huette.kasse.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrinksViewModel(application: Application): AndroidViewModel(application) {

    private val getAllDrinks: LiveData<List<Drink>>
    private val repository: DrinkRepository

    init{
        val drinkDao = AppDatabase.getDatabase(application).drinkDao()
        repository = DrinkRepository(drinkDao)
        getAllDrinks = repository.getAllDrinks
    }

    fun addDrink(drink: Drink){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDrink(drink)
        }
    }

}