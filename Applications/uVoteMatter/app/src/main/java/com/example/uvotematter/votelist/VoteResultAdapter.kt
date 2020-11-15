package com.example.uvotematter.votelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uvotematter.models.Vote
import com.example.uvotematter.databinding.VoteListResultItemBinding

class VoteResultAdapter : RecyclerView.Adapter<VoteResultAdapter.ViewHolder>() {

    private var votesList = listOf<Vote>()

    fun setList(list: List<Vote>) {
        votesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = VoteListResultItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(votesList[position])
    }

    override fun getItemCount() = votesList.size

    class ViewHolder(private val binding: VoteListResultItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Vote) {
            binding.vote = item
            binding.executePendingBindings()
        }
    }


}