package huette.kasse.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import huette.kasse.data.entities.UserDrinks

@Dao
interface UserDrinksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDrinkToUser(userDrink: UserDrinks)

    @Query("SELECT user_id, drink_id, firstname, lastname, paid FROM user_drinks, users WHERE user_drinks.user_id = users.id ORDER BY firstName, lastName ASC")
    fun getAllData(): LiveData<List<UserDrinks>>

}