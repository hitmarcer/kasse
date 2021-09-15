package huette.kasse.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "firstname")
    val firstName: String,
    @ColumnInfo(name = "lastname")
    val lastName: String,
    @ColumnInfo(name = "deleted")
    val deleted: Boolean,
    @ColumnInfo(name = "credit")
    val credit: Double,
){
    constructor(firstName: String, lastName: String): this(0, firstName, lastName, false, 0.0)
}