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

    @Query("SELECT * FROM user_drinks, users WHERE user_drinks.user_id = users.id ORDER BY firstName, lastName ASC")
    fun getAllData(): LiveData<List<UserDrinks>>

    @Query("SELECT SUM(price) FROM user_drinks LEFT JOIN drinks ON drink_id = drinks.id WHERE user_id = :userid AND NOT paid")
    fun getUnpaid(userid: Int): Double

    @Query("UPDATE user_drinks SET paid = 1 WHERE user_id = :userid")
    fun setPaid(userid: Int)

}