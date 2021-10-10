package com.jasminsp.parliamentmemberapp.memberlist

import android.app.Application
import androidx.lifecycle.*
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.repository.MemberRepository

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: ViewModel for communication between MemberList fragment and memberRepository, also
// defining fragment logic

class MemberListViewModel(parliamentData: ParliamentData, application: Application) :
    AndroidViewModel(application) {
    // LiveData for navigation
    private val _navigateToSelectedMember = MutableLiveData<ParliamentData?>()
    val navigateToSelectedMember: LiveData<ParliamentData?>
        get() = _navigateToSelectedMember


    // Getting members from repository and filtering to distinct members
    private val members = MemberRepository.memberData
    val member = Transformations.map(members) {
        members.value?.filter { it.party == parliamentData.party }
    }


    // When specific item is clicked, set the _navigateToSelectedProperty [MutableLiveData] to
    // parliamentData that was clicked on and show member details
    fun memberDetails(parliamentData: ParliamentData) {
        _navigateToSelectedMember.value = parliamentData
    }

    // Nulling the value of _navigateToSelectedItem to avoid navigation being triggered again
    // when user returns from detail view. Marks the navigation state to complete.
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedMember.value = null
    }
}