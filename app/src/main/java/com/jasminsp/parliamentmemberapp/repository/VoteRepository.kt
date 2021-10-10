package com.jasminsp.parliamentmemberapp.repository

import androidx.lifecycle.LiveData
import com.jasminsp.parliamentmemberapp.MyApp
import com.jasminsp.parliamentmemberapp.database.VoteData
import com.jasminsp.parliamentmemberapp.database.VoteDatabase

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: Communication between viewModels and VoteDatabase

object VoteRepository {
    // Getting voteData from the database
    private val voteDao = VoteDatabase.getInstance(MyApp.appContext).voteDatabaseDao
    val voteData: LiveData<List<VoteData>> = voteDao.getAllVotes()


    // Adding votes to the database
    suspend fun addVote(voteData: VoteData) {
        voteDao.insertOrUpdate(voteData)
    }
}