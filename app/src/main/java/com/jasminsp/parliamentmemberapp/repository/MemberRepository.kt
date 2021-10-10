package com.jasminsp.parliamentmemberapp.repository


import androidx.lifecycle.LiveData
import com.jasminsp.parliamentmemberapp.MyApp
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.database.ParliamentDatabase
import com.jasminsp.parliamentmemberapp.web.MemberApi


object MemberRepository {
    // Getting memberData from the database
    private val memberDao = ParliamentDatabase.getInstance(MyApp.appContext).parliamentDatabaseDao
    val memberData: LiveData<List<ParliamentData>> = memberDao.getAllMembers()


    // Checking any changes from API and updating the database
    suspend fun refreshData() {
        val pMembers = MemberApi.retrofitService.getMemberRecords()
        pMembers.forEach { memberDao.insertOrUpdate(it)}

    }
}