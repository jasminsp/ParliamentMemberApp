package com.jasminsp.parliamentmemberapp.comments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.databinding.FragmentCommentListBinding


class CommentList : Fragment() {

    private lateinit var binding: FragmentCommentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment_list, container, false)
    }
}