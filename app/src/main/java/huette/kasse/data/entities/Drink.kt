package huette.kasse.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinks")
data class Drink(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "drinkname")
    val drinkName: String,
    @ColumnInfo(name = "price")
    val price: Double,
) {
    constructor(drinkName: String, price: Double): this(0, drinkName, price)
}