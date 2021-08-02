package huette.kasse.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import java.sql.Date
import java.sql.Time
import java.sql.Timestamp

@Entity(tableName = "user_drinks", primaryKeys = ["user_id", "drink_id"], foreignKeys = arrayOf(
    ForeignKey(
        entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("user_id"), onDelete = CASCADE
    ),
    ForeignKey(
        entity = Drink::class, parentColumns = arrayOf("id"), childColumns = arrayOf("drink_id"), onDelete = CASCADE
    )
)
)
data class UserDrinks(
    @ColumnInfo(name = "user_id")
    val userID: Int,
    @ColumnInfo(name = "drink_id")
    val drinkID: Int,
    @ColumnInfo(name = "paid")
    val paid: Boolean,
    @ColumnInfo(name = "timestamp")
    val timeStamp: Long,
){
    constructor(user_id: Int, drink_id: Int) : this(user_id, drink_id, false, System.currentTimeMillis())
}