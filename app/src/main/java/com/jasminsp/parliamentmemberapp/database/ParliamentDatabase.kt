package com.jasminsp.parliamentmemberapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jasminsp.parliamentmemberapp.MyApp

//Creating the database
@Database(entities = [ParliamentData::class], version = 1, exportSchema = false)

abstract class ParliamentDatabase : RoomDatabase() {
    abstract val parliamentDatabaseDao: ParliamentMemberDao

    // Ensuring only one instance is created
    companion object {
        @Volatile
        private var INSTANCE: ParliamentDatabase? = null

        // Ensuring that database is only created once
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