package com.jasminsp.parliamentmemberapp.memberdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jasminsp.parliamentmemberapp.MyApp
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.bindImage
import com.jasminsp.parliamentmemberapp.database.CommentData
import com.jasminsp.parliamentmemberapp.database.VoteData
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

        binding.checkboxHeart.isChecked = viewModel.buttonCheckedState()
        binding.checkboxHeart.setOnClickListener {
            if (binding.checkboxHeart.isChecked) viewModel.voteValues = 1 else viewModel.voteValues = 0
            sendVotesToDatabase()
        }

        binding.btnSaveComments.setOnClickListener {addComment()}
        binding.btnViewComments.setOnClickListener {checkComments()}

        viewModel.memberVote.observe(viewLifecycleOwner, {
            binding.checkboxHeart.isChecked
        })


        return binding.root
    }

    // Sending votes to the database
    private fun sendVotesToDatabase() {
        val personNumber = viewModel.member?.personNumber ?: 0
        val vote = viewModel.voteValues

        val voteEntry = VoteData(personNumber, vote)
        viewModel.memberLiked(voteEntry)
    }

    private fun addComment() {
        viewModel.member?.let { viewModel.addComment(CommentData(viewModel.formatDate(), it.personNumber, binding.txtComments.text.toString())) }
        binding.txtComments.text?.clear()
        Toast.makeText(MyApp.appContext, "Saved", Toast.LENGTH_SHORT).show()
    }


    private fun checkComments(){
        viewModel.member?.let {
            this.findNavController().navigate(MemberDetailsDirections.toCommentList(it))
        }
    }
}
