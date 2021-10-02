package com.jasminsp.parliamentmemberapp.partylist

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.bumptech.glide.Glide.init
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.repository.MemberRepository
import kotlinx.coroutines.launch


// TODO possibly add some error handling here for recyclerview. Use android kotlin fundamentals: Loading and displaying images
// TODO start using viewmodelscope.launch to properly initialize the viewmodel


//Initialization of a viewModel
class PartyListViewModel: ViewModel() {
    // Creates navigation
    private val _navigateToSelectedItem = MutableLiveData<ParliamentData?>()
    val navigateToSelectedItem: LiveData<ParliamentData?>
        get() = _navigateToSelectedItem


    init {
        Log.i("bugging", "PartyViewModel Created")
    }

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
