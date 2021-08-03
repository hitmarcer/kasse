package huette.kasse.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import huette.kasse.data.daos.DrinkDao
import huette.kasse.data.daos.UserDao
import huette.kasse.data.daos.UserDrinksDao
import huette.kasse.data.entities.Drink
import huette.kasse.data.entities.User
import huette.kasse.data.entities.UserDrinks

@Database(entities = [User::class, Drink::class, UserDrinks::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun drinkDao(): DrinkDao
    abstract fun userDrinksDao(): UserDrinksDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database")
                    .allowMainThreadQueries() // Sollte nicht gemacht werden, aber funktioneirt wenigstens
                .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}