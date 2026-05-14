package com.pashuaahar.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pashuaahar.R
import com.pashuaahar.databinding.ItemBreedSelectionBinding

data class BreedUIModel(
    val nameKey: String,
    val nameRes: Int,
    val subRes: Int,
    val imageUrl: String,
    var isSelected: Boolean = false
)

class BreedSelectionAdapter(
    private val breeds: List<BreedUIModel>,
    private val onBreedSelected: (String) -> Unit
) : RecyclerView.Adapter<BreedSelectionAdapter.ViewHolder>() {

    private var selectedPosition = breeds.indexOfFirst { it.isSelected }.let { if (it == -1) 0 else it }

    inner class ViewHolder(val binding: ItemBreedSelectionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBreedSelectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val breed = breeds[position]
        holder.binding.tvBreedName.setText(breed.nameRes)
        holder.binding.tvBreedSub.setText(breed.subRes)
        
        Glide.with(holder.binding.ivBreed.context)
            .load(breed.imageUrl)
            .placeholder(R.drawable.ic_cow)
            .into(holder.binding.ivBreed)

        val isSelected = position == selectedPosition
        holder.binding.cardBreed.strokeWidth = if (isSelected) 6 else 2
        holder.binding.cardBreed.strokeColor = holder.binding.root.context.getColor(
            if (isSelected) R.color.green_700 else R.color.green_200
        )

        holder.binding.root.setOnClickListener {
            val oldPos = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(oldPos)
            notifyItemChanged(selectedPosition)
            onBreedSelected(breed.nameKey)
        }
    }

    override fun getItemCount() = breeds.size
}
