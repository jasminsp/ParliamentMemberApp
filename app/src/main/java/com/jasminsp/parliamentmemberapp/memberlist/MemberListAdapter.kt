package com.jasminsp.parliamentmemberapp.memberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.databinding.MemberViewItemBinding
import com.jasminsp.parliamentmemberapp.repository.VoteRepository

class MemberListAdapter(
    private val lifeCycle: LifecycleOwner,
    private val whileClicked: OnClickListener
) :
    ListAdapter<ParliamentData, MemberListAdapter.MemberListViewHolder>(MemberDiffCallback) {

    // Getting voteValues from the database
    private val voteRepository = VoteRepository
    val votes = voteRepository.voteData


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberListViewHolder {
        val binding =
            MemberViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MemberListViewHolder, position: Int) {
        val memberData = getItem(position)
        val fullName = memberData.first + " " + memberData.last
        val likeValue = votes.value?.find { it.personNumber == memberData.personNumber }?.voteValue

        // Using PartyViewHolder to set a right name for specific party
        holder.binding.txtMember.text = fullName

        // Observing the checkbox click state and weather there is a like
        votes.observe(lifeCycle, {
            var buttonState = R.drawable.ic_heart_foreground
            when (likeValue) {
                1 -> buttonState = R.drawable.ic_heart_pressed_foreground_small
                0 -> buttonState = R.drawable.ic_heart_enabled_foreground_small
            }
            holder.binding.imgLikeValue.setImageResource(buttonState)
        })

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