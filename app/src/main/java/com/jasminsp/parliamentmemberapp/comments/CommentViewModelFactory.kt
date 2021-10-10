package com.jasminsp.parliamentmemberapp.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jasminsp.parliamentmemberapp.database.ParliamentData

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: Error detection for creating anonymous objects

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