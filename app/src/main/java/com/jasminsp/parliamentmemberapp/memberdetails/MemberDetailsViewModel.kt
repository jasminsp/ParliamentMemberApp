package com.jasminsp.parliamentmemberapp.memberdetails

import android.app.Application
import androidx.lifecycle.*
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.repository.MemberRepository


// TODO possibly add some error handling here for recyclerview. Use android kotlin fundamentals: Loading and displaying images
// TODO start using viewmodelscope.launch to properly initialize the viewmodel

class MemberDetailsViewModel(parliamentData: ParliamentData, application: Application): AndroidViewModel(application) {

    // Getting members from repository and filtering to member according to item click
    private val members = MemberRepository.memberData
    val member = members.value?.find { it.personNumber == parliamentData.personNumber }

    var fullName: String = "${member?.first} ${member?.last}"

    var partyLogo = when (member?.party) {
        "sd" -> R.drawable.ic_sdp
        "vihr" -> R.drawable.ic_vihreat
        "kesk" -> R.drawable.ic_keskusta
        "vas" -> R.drawable.ic_vasemmisto
        "kd" -> R.drawable.ic_kristillis
        "kok" -> R.drawable.ic_kokoomus
        "ps" -> R.drawable.ic_perus
        "r" -> R.drawable.ic_r
        else -> R.drawable.ic_liike
    }
}