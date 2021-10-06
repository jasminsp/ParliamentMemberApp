package com.jasminsp.parliamentmemberapp.memberlist

import android.app.Application
import androidx.lifecycle.*
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.repository.MemberRepository

class MemberListViewModel(parliamentData: ParliamentData, application: Application) :
    AndroidViewModel(application) {
    // Creates navigation
    private val _navigateToSelectedMember = MutableLiveData<ParliamentData?>()
    val navigateToSelectedMember: LiveData<ParliamentData?>
        get() = _navigateToSelectedMember


    // Getting members from repository and filtering to distinct members
    private val members = MemberRepository.memberData
    val member = Transformations.map(members) {
        members.value?.filter { it.party == parliamentData.party }
    }


    // Showing the member details
    fun memberDetails(parliamentData: ParliamentData) {
        _navigateToSelectedMember.value = parliamentData
    }

    // Nulling the value of _navigateToSelectedItem to avoid navigation being triggered again
    // when user returns from detail view. Marks the navigation state to complete.
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedMember.value = null
    }

}