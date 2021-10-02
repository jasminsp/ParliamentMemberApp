package com.jasminsp.parliamentmemberapp.memberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.databinding.MemberViewItemBinding

class MemberListAdapter (private val whileClicked: OnClickListener) :
    ListAdapter<ParliamentData, MemberListAdapter.MemberListViewHolder>(MemberDiffCallback){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberListViewHolder {
        val binding = MemberViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MemberListViewHolder, position: Int) {
        val memberData = getItem(position)
        val fullName = memberData.first + " " + memberData.last

        // Using PartyViewHolder to set a right name for specific party
        holder.binding.txtMember.text = fullName


        holder.itemView.setOnClickListener {
            whileClicked.onClick(memberData)
        }
    }

    // Binding PartyList fragment to the layout
    class MemberListViewHolder(val binding: MemberViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // ListAdapter methods extending a DiffUtil callback
    // for calculating the difference between two non-null items in a list
    // returns true is areItemsTheSame object reference is the same
    companion object MemberDiffCallback : DiffUtil.ItemCallback<ParliamentData>() {
        override fun areItemsTheSame(oldItem: ParliamentData, newItem: ParliamentData): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ParliamentData, newItem: ParliamentData): Boolean {
            return oldItem.personNumber == newItem.personNumber
        }
    }


    class OnClickListener(val clickListener: (parliamentMember: ParliamentData) -> Unit) {
        fun onClick(parliamentMember: ParliamentData) = clickListener(parliamentMember)
    }
}