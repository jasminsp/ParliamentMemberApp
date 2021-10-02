package com.jasminsp.parliamentmemberapp.partylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.databinding.PartyViewItemBinding


class PartyListAdapter(private val whileClicked: OnClickListener) :
    ListAdapter<ParliamentData, PartyListAdapter.PartyViewHolder>(PartyDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyViewHolder {
        val binding = PartyViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PartyViewHolder, position: Int) {
        val partyData = getItem(position)

        // Using PartyViewHolder to set a right logo for specific party
        holder.binding.image.setImageResource(
            when (partyData.party) {
                "sd" -> R.drawable.ic_sdp
                "vihr" -> R.drawable.ic_vihreat
                "kesk" -> R.drawable.ic_keskusta
                "vas" -> R.drawable.ic_vasemmisto
                "kd" -> R.drawable.ic_kristillis
                "kok" -> R.drawable.ic_kokoomus
                "ps" -> R.drawable.ic_perus
                "r" -> R.drawable.ic_r
                else -> R.drawable.ic_liike
            })

        // Making logos clickable
        holder.itemView.setOnClickListener {
            whileClicked.onClick(partyData)
        }
    }

    // Binding PartyList fragment to the layout
    class PartyViewHolder(val binding: PartyViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // ListAdapter methods extending a DiffUtil callback.
    // Calculates the difference between two non-null items in a list
    // returns true if areItemsTheSame object reference is the same
    companion object PartyDiffCallback : DiffUtil.ItemCallback<ParliamentData>() {
        override fun areItemsTheSame(oldItem: ParliamentData, newItem: ParliamentData): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ParliamentData, newItem: ParliamentData): Boolean {
            return oldItem.personNumber == newItem.personNumber
        }
    }


    class OnClickListener(val clickListener: (parliamentParty:ParliamentData) -> Unit) {
        fun onClick(parliamentParty:ParliamentData) = clickListener(parliamentParty)
    }
}