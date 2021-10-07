package com.jasminsp.parliamentmemberapp.repository

import androidx.lifecycle.LiveData
import com.jasminsp.parliamentmemberapp.MyApp
import com.jasminsp.parliamentmemberapp.database.VoteData
import com.jasminsp.parliamentmemberapp.database.VoteDatabase
import com.jasminsp.parliamentmemberapp.database.VoteDatabaseDao

object VoteRepository {
    private val voteDao = VoteDatabase.getInstance(MyApp.appContext).voteDatabaseDao
    val voteData: LiveData<List<VoteData>> = voteDao.getAllVotes()


    suspend fun addVote(voteData: VoteData) {
        voteDao.insertOrUpdate(voteData)
    }
}