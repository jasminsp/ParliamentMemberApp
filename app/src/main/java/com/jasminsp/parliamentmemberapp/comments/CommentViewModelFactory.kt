package com.jasminsp.parliamentmemberapp.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jasminsp.parliamentmemberapp.database.ParliamentData

class CommentViewModelFactory(
    private val parliamentData: ParliamentData,
    private val application: Application
) : ViewModelProvider.Factory {

    // uses factory methods to deal with the problem of creating objects without
    // having to specify the exact class of the object that will be created.
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            return CommentViewModel(parliamentData, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}