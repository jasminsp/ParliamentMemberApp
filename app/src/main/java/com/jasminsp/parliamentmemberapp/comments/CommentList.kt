package com.jasminsp.parliamentmemberapp.comments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jasminsp.parliamentmemberapp.MyApp
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.databinding.CommentViewItemBinding
import com.jasminsp.parliamentmemberapp.databinding.FragmentCommentListBinding


class CommentList : Fragment() {

    private lateinit var binding: FragmentCommentListBinding
    private lateinit var viewModel: CommentViewModel
    private lateinit var commentAdapter: CommentListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val application = requireNotNull(activity).application
        val parliamentData = CommentListArgs.fromBundle(requireArguments()).savedComments
        val viewModelFactory = CommentViewModelFactory(parliamentData, application)


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comment_list, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CommentViewModel::class.java)
        commentAdapter = CommentListAdapter()


        binding.commentList.adapter = commentAdapter
        binding.commentList.layoutManager = LinearLayoutManager(MyApp.appContext)


        viewModel.comment.observe(viewLifecycleOwner, {
            commentAdapter.submitList(it)
        })


        binding.commentList.addItemDecoration(DividerItemDecoration(MyApp.appContext, DividerItemDecoration.VERTICAL))




        return binding.root
    }
}