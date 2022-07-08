package br.com.zup.simcityrickandmorty.data.datasource.local

import android.content.Context
import androidx.room.*
import br.com.zup.simcityrickandmorty.data.datasource.local.dao.CharactersDAO
import br.com.zup.simcityrickandmorty.data.model.CharactersResult

@Database(entities = [CharactersResult::class], version = 3)
@TypeConverters(Converters::class)
abstract class CharactersDatabase:RoomDatabase() {
    abstract fun charDao(): CharactersDAO

    companion object {
        @Volatile
        private var INSTANCE: CharactersDatabase? = null

        fun getDatabase(context: Context): CharactersDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersDatabase::class.java,
                    "rickmorty_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}