package com.jasminsp.parliamentmemberapp.repository

import androidx.lifecycle.LiveData
import com.jasminsp.parliamentmemberapp.MyApp
import com.jasminsp.parliamentmemberapp.database.CommentData
import com.jasminsp.parliamentmemberapp.database.CommentDatabase

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: Communication between viewModels and CommentDatabase

object CommentRepository {
    // Getting comments from the comment database
    private val commentDao = CommentDatabase.getInstance(MyApp.appContext).commentDatabaseDao
    val commentData: LiveData<List<CommentData>> = commentDao.getAllComments()

    // Saving comments in the comment database
    suspend fun addComment(commentData: CommentData) {
        commentDao.insertOrUpdate(commentData)

    }
}