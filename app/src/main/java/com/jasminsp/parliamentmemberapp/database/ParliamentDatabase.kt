package com.jasminsp.parliamentmemberapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jasminsp.parliamentmemberapp.MyApp

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: ParliamentDatabase

//Creating the database
@Database(entities = [ParliamentData::class], version = 8, exportSchema = false)
abstract class ParliamentDatabase : RoomDatabase() {
    abstract val parliamentDatabaseDao: ParliamentDatabaseDao

    // Ensuring only one instance is created
    companion object {
        @Volatile
        private var INSTANCE: ParliamentDatabase? = null

        // Ensuring that database is only created once, if null it will be created
        fun getInstance(context: Context): ParliamentDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        ParliamentDatabase::class.java, "parliamentMember_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}