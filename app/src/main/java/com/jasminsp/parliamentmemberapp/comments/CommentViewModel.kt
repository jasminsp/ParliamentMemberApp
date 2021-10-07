package com.jasminsp.parliamentmemberapp.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.jasminsp.parliamentmemberapp.database.CommentData
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.database.VoteData
import com.jasminsp.parliamentmemberapp.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentViewModel(parliamentData: ParliamentData, application: Application) :
    AndroidViewModel(application) {

    // Getting comments for matching personNumbers from repository
    private val commentRepository = CommentRepository
    private val comments = commentRepository.commentData
    val comment = Transformations.map(comments) {
        comments.value?.filter { it.personNumber == parliamentData.personNumber }
    }

    // Transferring comment data to the database
    fun updateComments(comment: CommentData) {
        viewModelScope.launch(Dispatchers.IO) {
            commentRepository.addComment(comment)
        }
    }
}