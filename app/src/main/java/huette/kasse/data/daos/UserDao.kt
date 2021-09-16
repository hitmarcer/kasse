package huette.kasse.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import huette.kasse.data.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users WHERE NOT deleted ORDER BY firstname, lastname ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE id = :user_id")
    fun getSingleUser(user_id: Int): User

    @Query("SELECT * FROM users WHERE id = :user_id AND deleted = 1")
    fun getSingleUserDeleted(user_id: Int): User

    @Query("SELECT * FROM users WHERE NOT deleted ORDER BY firstname, lastname ASC")
    fun getAllUsersList(): List<User>

    @Query("SELECT * FROM users WHERE firstname = :firstname AND lastname = :lastname")
    fun getUserByName(firstname: String, lastname: String): User

    @Query("UPDATE users SET deleted = 1 WHERE id = :user_id")
    fun setUserDeleted(user_id: Int)

    @Query("UPDATE users SET deleted = 0 WHERE id = :user_id")
    fun reactivateUser(user_id: Int)

    @Query("UPDATE users SET credit = :credit WHERE id = :user_id")
    fun setCredit(user_id: Int, credit: Double)

    @Query("SELECT credit FROM users WHERE id = :user_id")
    fun getCredit(user_id: Int): Double

    @Query("UPDATE users SET unpaidAmount = :unpaidAmount WHERE id = :user_id")
    fun setUnpaidAmount(user_id: Int, unpaidAmount: Double)

    @Query("SELECT unpaidAmount FROM users WHERE id = :user_id")
    fun getUnpaidAmount(user_id: Int): Double

}