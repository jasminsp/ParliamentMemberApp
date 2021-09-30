package com.jasminsp.parliamentmemberapp.partylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jasminsp.parliamentmemberapp.R
import com.jasminsp.parliamentmemberapp.database.ParliamentData
import com.jasminsp.parliamentmemberapp.databinding.FragmentPartyListBinding
import com.jasminsp.parliamentmemberapp.databinding.PartyViewBinding


class PartyListAdapter(private val whileClicked: OnClickListener) :
    ListAdapter<ParliamentData, PartyListAdapter.PartyViewHolder>(PartyDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyViewHolder {
        val binding = PartyViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PartyViewHolder, position: Int) {
        val partyData = getItem(position)

        // Using PartyViewHolder to set a right logo for specific party
        holder.binding.imgPartyLogo.setImageResource(
            when (partyData.party) {
                "sd" -> R.drawable.ic_sdp_foreground
                "vihr" -> R.drawable.ic_vihrea
                "kesk" -> R.drawable.ic_liike_nyt
                "vas" -> R.drawable.ic_vasemmistoliitto
                "kd" -> R.drawable.ic_kristillisdemokraatit
                "kok" -> R.drawable.ic_kokoomus
                "ps" -> R.drawable.ic_perussuomalaiset
                "r" -> R.drawable.ic_rkp
                else -> R.drawable.ic_liike_nyt
            })

        holder.itemView.setOnClickListener {
            whileClicked.onClick(partyData)
        }
    }

    // Binding PartyList fragment to the layout
    inner class PartyViewHolder(val binding: PartyViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    class OnClickListener(val clickListener: (parliamentParty:ParliamentData) -> Unit) {
        fun onClick(parliamentParty:ParliamentData) = clickListener(parliamentParty)
    }

    // ListAdapter methods extending a DiffUtil callback
    // for calculating the difference between two non-null items in a list
    // returns true is areItemsTheSame object reference is the same
    companion object PartyDiffCallback : DiffUtil.ItemCallback<ParliamentData>() {
        override fun areItemsTheSame(oldItem: ParliamentData, newItem: ParliamentData): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: ParliamentData, newItem: ParliamentData): Boolean {
            return oldItem.personNumber == newItem.personNumber
        }
    }
}