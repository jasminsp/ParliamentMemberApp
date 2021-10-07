package com.jasminsp.parliamentmemberapp.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jasminsp.parliamentmemberapp.database.CommentData
import com.jasminsp.parliamentmemberapp.databinding.FragmentCommentListBinding
import com.jasminsp.parliamentmemberapp.repository.CommentRepository



class CommentListAdapter(private val whileClicked: OnClickListener) :
    ListAdapter<CommentData, CommentListAdapter.CommentListViewHolder>(CommentDiffCallback) {

    // Getting comments from the database
    private val commentRepository = CommentRepository
    val comments = commentRepository.commentData


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListViewHolder {
        val binding =
            FragmentCommentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CommentListViewHolder, position: Int) {
        val commentData = getItem(position)



        holder.itemView.setOnClickListener {
            whileClicked.onClick(commentData)
        }
    }

    // Binding CommentList fragment to the layout
    class CommentListViewHolder(val binding: FragmentCommentListBinding) :
        RecyclerView.ViewHolder(binding.root)


    // ListAdapter methods extending a DiffUtil callback
    // for calculating the difference between two non-null items in a list
    // returns true is areItemsTheSame object reference is the same
    companion object CommentDiffCallback : DiffUtil.ItemCallback<CommentData>() {
        override fun areItemsTheSame(oldItem: CommentData, newItem: CommentData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CommentData, newItem: CommentData): Boolean {
            return oldItem.personNumber == newItem.personNumber
        }
    }

    class OnClickListener(val clickListener: (commentData: CommentData) -> Unit) {
        fun onClick(commentData: CommentData) = clickListener(commentData)
    }
}