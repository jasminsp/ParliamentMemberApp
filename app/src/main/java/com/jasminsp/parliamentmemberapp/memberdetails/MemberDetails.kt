package com.jasminsp.parliamentmemberapp.memberdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.bindImage
import com.jasminsp.parliamentmemberapp.databinding.FragmentMemberDetailsBinding


class MemberDetails : Fragment() {

    private lateinit var binding: FragmentMemberDetailsBinding
    private lateinit var viewModel: MemberDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val application = requireNotNull(activity).application
        val parliamentData = MemberDetailsArgs.fromBundle(requireArguments()).selectedMember
        val viewModelFactory = MemberDetailsViewModelFactory(parliamentData, application)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_details, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MemberDetailsViewModel::class.java)

        bindImage(binding.imgMember, "https://avoindata.eduskunta.fi/${viewModel.member?.picture}")
        binding.txtMember.text = viewModel.fullName

        return binding.root
    }
}