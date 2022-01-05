package com.chebdowski.pokerdemo.presentation.ringgames

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chebdowski.pokerdemo.databinding.ItemRingBinding
import com.chebdowski.pokerdemo.domain.Ring

class RingGamesAdapter(private val onClick: (Ring) -> Unit) : ListAdapter<Ring, RingGamesAdapter.ViewHolder>(RingDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ring = getItem(position)
        holder.bind(ring, onClick)
    }

    class ViewHolder(private val binding: ItemRingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ring: Ring, onClick: (Ring) -> Unit) {
            binding.name.text = ring.name
            binding.gameType.text = ring.gameType
            binding.root.setOnClickListener { onClick(ring) }
        }
    }

    object RingDiffCallback : DiffUtil.ItemCallback<Ring>() {
        override fun areItemsTheSame(oldItem: Ring, newItem: Ring): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Ring, newItem: Ring): Boolean {
            return oldItem.name == newItem.name
        }
    }
}