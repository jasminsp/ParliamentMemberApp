package com.jasminsp.parliamentmemberapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jasminsp.parliamentmemberapp.databinding.FragmentPartyListBinding
import com.jasminsp.parliamentmemberapp.memberlist.MemberList
import com.jasminsp.parliamentmemberapp.partylist.PartyList


class MainActivity : AppCompatActivity() {

    // MainActivity only responsible for setting the content view that contains the
    // Navigation host. It is the base of all the Fragments. Also holds the bottom navigation bar and
    // its functionality

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Setting up the bottom navigation bar onClickListener
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.back -> {
                    super.onBackPressed()
                    true
                }
                R.id.parties -> {
                    findNavController(R.id.myNavHostFragment).navigate(R.id.partyList)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}




