package com.jasminsp.parliamentmemberapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

//Defining the datatable to be stored
@Entity(tableName = "member_database")
data class ParliamentData(
    @PrimaryKey
    val personNumber: Int,
    val seatNumber: Int,
    val last: String,
    val first: String,
    val party: String,
    val minister: Boolean,
    val picture: String,
    val bornYear: Int,
    val constituency: String
)

//Defining functions that can be used with the database
    @Dao
    interface ParliamentMemberDao {

        //Checking database for conflict, replacing data if any
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertOrUpdate(parliament: ParliamentData)

        //Get all members as livedata
        @Query("select * from member_database")
        fun getAllMembers(): LiveData<List<ParliamentData>>
    }
