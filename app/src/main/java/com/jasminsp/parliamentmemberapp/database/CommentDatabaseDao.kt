package com.jasminsp.parliamentmemberapp.database

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.parcelize.Parcelize

// Defining the datatable to be stored
// It will be parcelable so data can be divided in fragment navigation
@Parcelize
@Entity(tableName = "comment_database")
data class CommentData(
    @PrimaryKey()
    val date: String,
    val personNumber: Int,
    val comment: String
) : Parcelable


//Defining functions that can be used with the database
@Dao
interface CommentDatabaseDao {
    //Checking database for conflict, replacing data if any, inserting if none
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(comment: CommentData)

    //Get all comments as livedata from the comment database
    @Query("SELECT * FROM comment_database ORDER BY date")
    fun getAllComments(): LiveData<List<CommentData>>
}
