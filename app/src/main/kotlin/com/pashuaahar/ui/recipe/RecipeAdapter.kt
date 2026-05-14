package com.pashuaahar.ui.recipe

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pashuaahar.data.model.RecipeItem
import com.pashuaahar.databinding.ItemRecipeIngredientBinding

class RecipeAdapter : ListAdapter<RecipeItem, RecipeAdapter.VH>(DIFF) {

    inner class VH(val b: ItemRecipeIngredientBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemRecipeIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        val b = holder.b
        b.tvIngEmoji.text   = item.ingredient.emoji
        b.tvIngName.text    = item.ingredient.name
        b.tvIngLocal.text   = item.ingredient.localName
        b.tvIngQty.text     = item.displayQuantity
        b.tvIngCost.text    = "₹${item.costForItem.toInt()}/day"
        b.accentBar.setBackgroundColor(Color.parseColor(item.ingredient.colorHex))
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<RecipeItem>() {
            override fun areItemsTheSame(a: RecipeItem, b: RecipeItem) =
                a.ingredient.id == b.ingredient.id
            override fun areContentsTheSame(a: RecipeItem, b: RecipeItem) = a == b
        }
    }
}
