package com.jasminsp.parliamentmemberapp.memberdetails

import android.app.Application
import androidx.lifecycle.*
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.database.VoteData
import com.jasminsp.parliamentmemberapp.repository.MemberRepository
import kotlinx.coroutines.launch
import com.jasminsp.parliamentmemberapp.repository.VoteRepository
import kotlinx.coroutines.Dispatchers


// TODO possibly add some error handling here for recyclerview. Use android kotlin fundamentals: Loading and displaying images
// TODO start using viewmodelscope.launch to properly initialize the viewmodel

class MemberDetailsViewModel(parliamentData: ParliamentData, application: Application) :
    AndroidViewModel(application) {

    // Getting members from repository and filtering to member according to item click
    private val members = MemberRepository.memberData
    val member = members.value?.find { it.personNumber == parliamentData.personNumber }


    // getting the vote data from the database
    var voteValues = 0
    private var buttonState: Boolean = false
    private val voteRepository = VoteRepository
    private val votes = voteRepository.voteData
    val memberVote = Transformations.map(votes) { it ->
        it.find { it.personNumber == parliamentData.personNumber }
    }

    init {
        buttonCheckedState()
    }

    // Getting the likeValue from the database and
    // Converting the button state from database 1 and 0 to true or false
    private val likeValue = votes.value?.find { it.personNumber == parliamentData.personNumber}?.voteValue
    fun buttonCheckedState(): Boolean {
        when (likeValue) {
            1 -> buttonState = true
            0 -> buttonState =false
            else -> println("Error with button state")
        }
        return buttonState
    }

    var fullName: String = "${member?.first} ${member?.last}"


    // Transferring vote data to the database
    fun memberLiked(vote: VoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            voteRepository.addVote(vote)
        }
    }
}