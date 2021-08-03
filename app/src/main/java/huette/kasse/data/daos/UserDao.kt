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

    @Query("SELECT * FROM users ORDER BY firstname, lastname ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE id = :user_id")
    fun getSingleUser(user_id: Int): User

    @Query("SELECT * FROM users ORDER BY firstname, lastname ASC")
    fun getAllUsersList(): List<User>

}