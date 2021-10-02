package com.jasminsp.parliamentmemberapp.partylist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jasminsp.parliamentmemberapp.MyApp
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.databinding.FragmentPartyListBinding
import com.jasminsp.parliamentmemberapp.memberlist.MemberList
import com.jasminsp.parliamentmemberapp.memberlist.MemberListDirections


class PartyList : Fragment() {

    private lateinit var viewModel: PartyListViewModel
    private lateinit var binding: FragmentPartyListBinding
    private lateinit var partyAdapter: PartyListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initializing the variables
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)
        viewModel = ViewModelProvider(this).get(PartyListViewModel::class.java)


        // Triggering the LiveData in the view model for the navigation
        partyAdapter = PartyListAdapter(PartyListAdapter.OnClickListener {
            viewModel.partyDetails(it)
        })

        binding.partyGridView.adapter = partyAdapter
        binding.partyGridView.layoutManager = GridLayoutManager(MyApp.appContext, 2)

        viewModel.parties.observe(viewLifecycleOwner, {
            partyAdapter.submitList(it)
        })

        viewModel.navigateToSelectedItem.observe(viewLifecycleOwner, {
            if ( null != it ) {
                this.findNavController().navigate(
                    PartyListDirections.actionShowMemberList(it))
                viewModel.displayPropertyDetailsComplete()
        }
        })

        return binding.root
    }
}