package com.jasminsp.parliamentmemberapp.memberdetails

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.jasminsp.parliamentmemberapp.MyApp
import com.jasminsp.parliamentmemberapp.database.CommentData
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.database.VoteData
import com.jasminsp.parliamentmemberapp.repository.CommentRepository
import com.jasminsp.parliamentmemberapp.repository.MemberRepository
import kotlinx.coroutines.launch
import com.jasminsp.parliamentmemberapp.repository.VoteRepository
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: ViewModel for communication between MemberDetails fragment and member/voteRepository and
// defining fragment logic

class MemberDetailsViewModel(parliamentData: ParliamentData, application: Application) :
    AndroidViewModel(application) {

    private val voteRepository = VoteRepository
    private val commentRepository = CommentRepository


    // Getting members from repository and filtering to member according to item click
    private val members = MemberRepository.memberData
    val member = members.value?.find { it.personNumber == parliamentData.personNumber }

    // Getting the voteData from the database
    private val votes = voteRepository.voteData
    val memberVote = Transformations.map(votes) { it ->
        it.find { it.personNumber == parliamentData.personNumber } }


    // getting the likeValue (0 or 1) from the database
    var voteValues = 0
    private var buttonState: Boolean = false
    private val likeValue = votes.value?.find { it.personNumber == parliamentData.personNumber}?.voteValue


    var fullName: String = "${member?.first} ${member?.last}"


    init {
        heartCheckedState()
    }


    // Converting the button state from database 1 and 0 to true or false
    fun heartCheckedState(): Boolean {
        when (likeValue) {
            1 -> buttonState = true
            0 -> buttonState =false
            else -> println("Error with button state")
        }
        return buttonState
    }


    // Formatting date for commentList
    fun formatDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss ", Locale.getDefault())
        return sdf.format(Date())
    }


    // Adding votes to the database
    fun addVotes(vote: VoteData){
        viewModelScope.launch {
            try {
                voteRepository.addVote(vote)
            } catch (networkError: IOException) {
                Toast.makeText(
                    MyApp.appContext, "$networkError",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    // Adding comment to the database
    fun addComment(comment: CommentData){
        viewModelScope.launch {
            try {
                commentRepository.addComment(comment)
            } catch (networkError: IOException) {
                Toast.makeText(
                    MyApp.appContext, "$networkError",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}