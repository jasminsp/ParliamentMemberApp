package com.jasminsp.parliamentmemberapp.memberlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jasminsp.parliamentmemberapp.database.ParliamentData

class MemberListViewModelFactory(private val parliamentData: ParliamentData, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MemberListViewModel::class.java)) {
                return MemberListViewModel(parliamentData, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
