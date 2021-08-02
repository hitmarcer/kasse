package huette.kasse.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDrinksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDrinkToUser(userDrink: UserDrinks)

    @Query("SELECT user_id, drink_id, firstname, lastname, amount FROM user_drinks, users WHERE user_drinks.user_id = users.id ORDER BY firstName, lastName ASC")
    fun getAllData(): LiveData<List<UserDrinks>>

}