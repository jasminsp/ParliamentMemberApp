package com.jasminsp.parliamentmemberapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jasminsp.parliamentmemberapp.MyApp

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: VoteDatabase

//Creating the database
@Database(entities = [VoteData::class], version = 2, exportSchema = false)
abstract class VoteDatabase : RoomDatabase() {
    abstract val voteDatabaseDao: VoteDatabaseDao

    // Ensuring only one instance is created
    companion object {
        @Volatile
        private var INSTANCE: VoteDatabase? = null

        // Ensuring that database is only created once, if null it will be created
        fun getInstance(context: Context): VoteDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        VoteDatabase::class.java, "vote_database"
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