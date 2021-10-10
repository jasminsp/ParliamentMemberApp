package com.jasminsp.parliamentmemberapp.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.repository.CommentRepository

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: ViewModel for communication between CommentList fragment and CommentRepository

class CommentViewModel(parliamentData: ParliamentData, application: Application) :
    AndroidViewModel(application) {

    // Getting comments for matching personNumbers from repository
    private val commentRepository = CommentRepository
    private val comments = commentRepository.commentData

    // We get comments filtered by specific member
    val comment = Transformations.map(comments) {
        comments.value?.filter { it.personNumber == parliamentData.personNumber }
    }
}