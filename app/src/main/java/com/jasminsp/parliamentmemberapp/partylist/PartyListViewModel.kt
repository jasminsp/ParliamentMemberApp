package com.jasminsp.parliamentmemberapp.partylist


import androidx.lifecycle.*
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.repository.MemberRepository

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: ViewModel for communication between PartyList fragment and memberRepository, also
// defining fragment logic

//Initialization of a viewModel
class PartyListViewModel : ViewModel() {
    // Creates navigation
    private val _navigateToSelectedItem = MutableLiveData<ParliamentData?>()
    val navigateToSelectedItem: LiveData<ParliamentData?>
        get() = _navigateToSelectedItem


    // Getting members from repository and filtering to distinct parties
    private val members = MemberRepository.memberData
    val parties = Transformations.map(members) {
        members.value?.distinctBy { it.party }
    }


    // sets _navigateToSelectedItem to the selected item.
    fun partyDetails(parliamentData: ParliamentData) {
        _navigateToSelectedItem.value = parliamentData
    }

    // Nulling the value of _navigateToSelectedItem to avoid navigation being triggered again
    // when user returns from detail view. Marks the navigation state to complete.
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedItem.value = null
    }
}
