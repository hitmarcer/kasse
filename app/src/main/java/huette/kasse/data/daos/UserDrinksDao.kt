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

    @Query("DELETE FROM user_drinks WHERE user_id = :user_id AND drink_id = :drink_id AND timestamp = :timestamp")
    fun deleteDrinkFromUser(user_id: Int, drink_id: Int, timestamp: Long)

    @Query("SELECT * FROM user_drinks WHERE NOT paid ORDER BY user_id ASC")
    fun getUsersWithData(): List<UserDrinks>

    @Query("SELECT COUNT() FROM user_drinks WHERE user_id = :user_id AND drink_id = :drink_id AND paid = 0")
    fun getDrinkAmount(user_id: Int, drink_id: Int): Int

}