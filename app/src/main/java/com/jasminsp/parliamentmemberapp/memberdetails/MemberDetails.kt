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

        // Sets info for name and image
        bindImage(binding.imgMember, "https://avoindata.eduskunta.fi/${viewModel.member?.picture}")
        binding.txtMember.text = viewModel.fullName

        // Checking heartButton value when taking from database and before saving to database
        binding.checkboxHeart.isChecked = viewModel.heartCheckedState()
        binding.checkboxHeart.setOnClickListener {
            if (binding.checkboxHeart.isChecked)
                viewModel.voteValues = 1 else viewModel.voteValues = 0
            addVotes()
        }

        binding.btnSaveComments.setOnClickListener { addComment() }
        binding.btnViewComments.setOnClickListener { showComments() }

        // Observes votes from repository
        viewModel.memberVote.observe(viewLifecycleOwner, {
            binding.checkboxHeart.isChecked
        })

        return binding.root
    }

    // Adding votes to the database
    private fun addVotes() {
        viewModel.member?.let {
            viewModel.addVotes(
                VoteData(
                    it.personNumber,
                    viewModel.voteValues
                )
            )
        }
    }

    // Adding comments to the database
    private fun addComment() {
        viewModel.member?.let {
            viewModel.addComment(
                CommentData(
                    viewModel.formatDate(),
                    it.personNumber,
                    binding.txtComments.text.toString()
                )
            )
        }
        binding.txtComments.text?.clear()
        Toast.makeText(MyApp.appContext, "Saved", Toast.LENGTH_SHORT).show()
    }


    // Navigate to commentList Fragment with member specific comments
    private fun showComments() {
        viewModel.member?.let {
            this.findNavController().navigate(MemberDetailsDirections.toCommentList(it))
        }
    }
}
