package huette.kasse.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users ORDER BY firstname, lastname ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT paid FROM users WHERE firstName LIKE :firstName AND lastName LIKE :lastName")
    suspend fun getBezahlt(firstName: String, lastName: String): Boolean

}