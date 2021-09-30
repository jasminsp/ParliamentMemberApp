package com.jasminsp.parliamentmemberapp.partylist

import androidx.lifecycle.*
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.repository.MemberRepository

// TODO

//Initialization of a viewModel
class PartyListViewModel : ViewModel() {

    // No duplicate parties
    private val members = MemberRepository.memberData
    val parties = Transformations.map(members) {
        members.value?.forEach { it.party }
    }

    private val _parliamentParties = MutableLiveData<ParliamentData?>()
    val parliamentMembers: LiveData<ParliamentData?>
        get() = _parliamentParties


    // Showing the party details
    fun partyDetails(parliamentData: ParliamentData) {
        _parliamentParties.value = parliamentData
    }
}
