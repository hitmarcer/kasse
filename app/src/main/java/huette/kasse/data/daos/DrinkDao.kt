package huette.kasse.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import huette.kasse.data.entities.Drink

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDrink(drink: Drink)

    @Query("SELECT * FROM drinks ORDER BY id ASC")
    fun getAllDrinks(): LiveData<List<Drink>>

    @Query("SELECT * FROM drinks WHERE id = :drink_id")
    fun getSingleDrink(drink_id: Int): Drink

    @Query("SELECT * FROM drinks WHERE drinkname = :name")
    fun getDrinkByName(name: String): Drink

    @Query("SELECT * FROM drinks ORDER BY drinkname ASC")
    fun getAllDrinksList(): List<Drink>

}