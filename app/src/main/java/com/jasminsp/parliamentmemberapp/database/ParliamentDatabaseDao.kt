package com.jasminsp.parliamentmemberapp.database

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.parcelize.Parcelize


// Defining the datatable to be stored
// It will be parcelable so data can be divided in fragment navigation
@Parcelize
@Entity(tableName = "member_database")
data class ParliamentData(
    @PrimaryKey(autoGenerate = false)
    val personNumber: Int,
    val seatNumber: Int,
    val last: String,
    val first: String,
    val party: String,
    val minister: Boolean,
    val picture: String,
    val twitter: String,
    val bornYear: Int,
    val constituency: String
) : Parcelable


//Defining functions that can be used with the database
    @Dao
    interface ParliamentDatabaseDao {
        //Checking database for conflict, replacing data if any
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertOrUpdate(parliament: ParliamentData)

        //Get all members as livedata from the member_database
        @Query("SELECT * FROM member_database ORDER BY personNumber")
        fun getAllMembers(): LiveData<List<ParliamentData>>
    }
