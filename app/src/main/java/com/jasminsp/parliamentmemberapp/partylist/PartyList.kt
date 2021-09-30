package com.jasminsp.parliamentmemberapp.partylist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.databinding.FragmentPartyListBinding
import com.jasminsp.parliamentmemberapp.repository.MemberRepository


class PartyList : Fragment() {

    private lateinit var viewModel: PartyListViewModel
    private lateinit var binding: FragmentPartyListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)
        viewModel = ViewModelProvider(this).get(PartyListViewModel::class.java)


        return binding.root
    }
}