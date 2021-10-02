package com.jasminsp.parliamentmemberapp.memberlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.repository.MemberRepository

class MemberListViewModel(parliamentData: ParliamentData, application: Application): ViewModel() {
    // Creates navigation
    private val _navigateToSelectedItem = MutableLiveData<ParliamentData?>()
    val navigateToSelectedItem: LiveData<ParliamentData?>
        get() = _navigateToSelectedItem


    init {
        Log.i("bugging", "MemberListViewModel Created")
    }

    // Getting members from repository and filtering to distinct members
    private val members = MemberRepository.memberData
    val member = Transformations.map(members) {
        members.value?.filter { it.party == parliamentData.party }
    }


    // Showing the member details
    fun memberDetails(parliamentData: ParliamentData) {
        _navigateToSelectedItem.value = parliamentData
    }

    // Nulling the value of _navigateToSelectedItem to avoid navigation being triggered again
    // when user returns from detail view. Marks the navigation state to complete.
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedItem.value = null
    }
}