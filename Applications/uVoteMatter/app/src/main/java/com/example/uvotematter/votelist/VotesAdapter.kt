package com.example.uvotematter.votelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uvotematter.databinding.VoteListItemBinding

class VotesAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<VotesAdapter.ViewHolder>() {

    private val numberList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = VoteListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(numberList[position], onClickListener)
    }

    override fun getItemCount() = numberList.size

    class ViewHolder(private val binding: VoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, onClickListener: OnClickListener) {
            binding.number = item
            binding.votePointButton.setOnClickListener {
                onClickListener.onClick(item)
            }
            binding.executePendingBindings()
        }
    }

}

class OnClickListener(val clickListener: (votePoint: String) -> Unit) {
    fun onClick(votePoint: String) = clickListener(votePoint)
}