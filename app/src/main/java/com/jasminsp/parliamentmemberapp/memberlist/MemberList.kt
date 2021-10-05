package com.jasminsp.parliamentmemberapp.memberlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jasminsp.parliamentmemberapp.MyApp
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.databinding.FragmentMemberListBinding

class MemberList : Fragment() {

    private lateinit var viewModel: MemberListViewModel
    private lateinit var binding: FragmentMemberListBinding
    private lateinit var memberAdapter: MemberListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(activity).application
        val parliamentData = MemberListArgs.fromBundle(requireArguments()).selectedParty
        val viewModelFactory = MemberListViewModelFactory(parliamentData, application)


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MemberListViewModel::class.java)
        memberAdapter = MemberListAdapter(MemberListAdapter.OnClickListener {
            viewModel.memberDetails(it)
        })


        binding.memberListView.adapter = memberAdapter
        binding.memberListView.layoutManager = LinearLayoutManager(MyApp.appContext)


        viewModel.member.observe(viewLifecycleOwner, {
            memberAdapter.submitList(it)
        })


        viewModel.navigateToSelectedMember.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController().navigate(
                    MemberListDirections.actionShowMemberDetails(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })



        return binding.root
    }
}