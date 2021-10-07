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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.back -> {
                    super.onBackPressed()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}




