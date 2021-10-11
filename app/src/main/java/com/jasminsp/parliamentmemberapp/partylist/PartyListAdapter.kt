package com.jasminsp.parliamentmemberapp.partylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.databinding.PartyViewItemBinding


// Student: Jasmin Partanen
// Student ID: 2012207
// Date: 10.10.2021
// Class Description: Adapter for the PartyList fragment recyclerView

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
                "sd" -> R.mipmap.ic_sdp
                "vihr" -> R.mipmap.ic_vihreat
                "kesk" -> R.mipmap.ic_keskusta
                "vas" -> R.mipmap.ic_vasemmisto
                "kd" -> R.mipmap.ic_kristillis
                "kok" -> R.mipmap.ic_kokoomus
                "ps" -> R.mipmap.ic_perus
                "r" -> R.mipmap.ic_r
                else -> R.mipmap.ic_liike
            }
        )

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

    class OnClickListener(val clickListener: (parliamentParty: ParliamentData) -> Unit) {
        fun onClick(parliamentParty: ParliamentData) = clickListener(parliamentParty)
    }
}