package com.jasminsp.parliamentmemberapp.database

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.parcelize.Parcelize

// Defining the datatable to be stored
// It will be parcelable so data can be divided in fragment navigation
@Parcelize
@Entity(tableName = "vote_database")
data class VoteData(
    @PrimaryKey(autoGenerate = false)
    val personNumber: Int,
    val voteValue: Int = 0
) : Parcelable


//Defining functions that can be used with the database
@Dao
interface VoteDatabaseDao {
    //Checking database for conflict, replacing data if any, inserting if none
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(vote: VoteData)

    //Get all votes as livedata from the vote database
    @Query("SELECT * FROM vote_database ORDER BY personNumber")
    fun getAllVotes(): LiveData<List<VoteData>>
}
