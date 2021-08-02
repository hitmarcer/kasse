package huette.kasse.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDrink(drink: Drink)

    @Query("SELECT * FROM drinks ORDER BY id ASC")
    fun getAllDrinks(): LiveData<List<Drink>>

}