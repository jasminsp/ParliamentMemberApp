package com.jasminsp.parliamentmemberapp.partylist

import androidx.lifecycle.*
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.repository.MemberRepository


// TODO possibly add some error handling here for recyclerview. Use android kotlin fundamentals: Loading and displaying images

//Initialization of a viewModel
class PartyListViewModel: ViewModel() {

    // No duplicate parties
    val memberRepository = MemberRepository
    val members = memberRepository.memberData
    val parties = Transformations.map(members) {
        members.value?.distinctBy { it.party }
    }

    private val _parliamentParties = MutableLiveData<ParliamentData?>()
    val parliamentParties: LiveData<ParliamentData?>
        get() = _parliamentParties


    // Showing the party details
    fun partyDetails(parliamentData: ParliamentData) {
        _parliamentParties.value = parliamentData
    }


}
