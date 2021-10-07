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
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


// TODO possibly add some error handling here for recyclerview. Use android kotlin fundamentals: Loading and displaying images
// TODO start using viewmodelscope.launch to properly initialize the viewmodel

class MemberDetailsViewModel(parliamentData: ParliamentData, application: Application) :
    AndroidViewModel(application) {

    private val voteRepository = VoteRepository
    private val commentRepository = CommentRepository


    // Getting members from repository and filtering to member according to item click
    private val members = MemberRepository.memberData
    val member = members.value?.find { it.personNumber == parliamentData.personNumber }


    // getting the vote data from the database
    var voteValues = 0
    private var buttonState: Boolean = false
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


    fun formatDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss ", Locale.getDefault())
        return sdf.format(Date())
    }


    // Transferring vote data to the database
    fun memberLiked(vote: VoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            voteRepository.addVote(vote)
        }
    }

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