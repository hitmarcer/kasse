package huette.kasse.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import huette.kasse.data.AppDatabase
import huette.kasse.data.entities.Drink
import huette.kasse.data.repositories.DrinkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrinksViewModel(application: Application): AndroidViewModel(application) {

    val getAllDrinks: LiveData<List<Drink>>
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

    fun getSingleDrink(drink_id: Int): Drink {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSingleDrink(drink_id)
        }
        return repository.getSingleDrink(drink_id)
    }

    fun getDrinkByName(name: String): Drink {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDrinkByName(name)
        }
        return repository.getDrinkByName(name)
    }

    fun getAllDrinksList(): List<Drink> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllDrinksList()
        }
        return repository.getAllDrinksList()
    }

}