package com.alex3g.moneyflow.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alex3g.moneyflow.data.dao.UserDao
import com.alex3g.moneyflow.data.models.User

@Database([User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun transactionDao(): UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    name = "moneyflow.db"
                ).build().also {
                    instance = it
                }
            }
        }
    }

}