package com.pashuaahar.ui.breeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pashuaahar.data.model.CowBreed
import com.pashuaahar.databinding.ItemCowBreedBinding

class BreedsAdapter(private val onItemClick: (CowBreed) -> Unit) :
    ListAdapter<CowBreed, BreedsAdapter.BreedViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val binding = ItemCowBreedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BreedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = getItem(position)
        holder.bind(breed)
    }

    inner class BreedViewHolder(private val binding: ItemCowBreedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(breed: CowBreed) {
            binding.breedName.text = breed.name
            binding.breedOrigin.text = breed.origin
            binding.breedMilkCapacity.text = breed.milkCapacity
            
            Glide.with(binding.breedImage.context)
                .load(breed.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(binding.breedImage)

            binding.root.setOnClickListener { onItemClick(breed) }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CowBreed>() {
        override fun areItemsTheSame(oldItem: CowBreed, newItem: CowBreed): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CowBreed, newItem: CowBreed): Boolean = oldItem == newItem
    }
}
