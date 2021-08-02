package huette.kasse.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinks")
data class Drink(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "drink_id")
    val drinkID: String,
    @ColumnInfo(name = "drinkname")
    val drinkName: String,
    @ColumnInfo(name = "price")
    val price: Double,
)