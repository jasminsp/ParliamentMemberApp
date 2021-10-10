package com.jasminsp.parliamentmemberapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: Setting content view that contains Navigation host. Base of all fragments. Holds
// Bottom navigation bar and its functionality

class MainActivity : AppCompatActivity() {

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




