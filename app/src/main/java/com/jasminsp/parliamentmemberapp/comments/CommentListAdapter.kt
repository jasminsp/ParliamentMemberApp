package com.jasminsp.parliamentmemberapp.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jasminsp.parliamentmemberapp.database.CommentData
import com.jasminsp.parliamentmemberapp.databinding.CommentViewItemBinding
import com.jasminsp.parliamentmemberapp.repository.CommentRepository


class CommentListAdapter :
    ListAdapter<CommentData, CommentListAdapter.CommentListViewHolder>(CommentDiffCallback) {

    // Getting commentData from the repository
    private val commentRepository = CommentRepository
    val comments = commentRepository.commentData


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListViewHolder {
        val binding = CommentViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentListViewHolder(binding)
    }

    // Setting the updated comment and date text with holder
    override fun onBindViewHolder(holder: CommentListViewHolder, position: Int) {
        val commentData = getItem(position)
        holder.binding.txtCommentDate.text = commentData.date
        holder.binding.txtUserComment.text = commentData.comment
    }


    // Binding CommentList fragment to the layout
    class CommentListViewHolder(val binding: CommentViewItemBinding) :
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
}