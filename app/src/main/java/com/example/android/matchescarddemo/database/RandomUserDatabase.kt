package com.example.android.matchescarddemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android.matchescarddemo.data.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Result::class], version = 4, exportSchema = false)
abstract class RandomUserDatabase : RoomDatabase() {

//    abstract val randomUserActionDAO: RandomUserActionDAO
    abstract fun randomUserDao(): RandomUserActionDAO

    private class UserDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var randomUserDao = database.randomUserDao()

                    // Delete all content here.
//                    randomUserDao.deleteAll()

                    // Add sample words.

                }
            }
        }
    }


    companion object {

        @Volatile
        private var INSTANCE: RandomUserDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): RandomUserDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RandomUserDatabase::class.java,
                        "word_database"
                )
                        .addCallback(UserDatabaseCallback(scope))
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}