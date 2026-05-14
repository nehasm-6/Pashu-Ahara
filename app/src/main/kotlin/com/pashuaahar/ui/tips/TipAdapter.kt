package com.pashuaahar.ui.tips

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pashuaahar.data.model.VetTip
import com.pashuaahar.databinding.ItemTipCardBinding

class TipAdapter : ListAdapter<VetTip, TipAdapter.VH>(DIFF) {

    inner class VH(val b: ItemTipCardBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemTipCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val tip = getItem(position)
        val b   = holder.b
        b.tvTipEmoji.text = tip.emoji
        b.tvTipTitle.text = tip.title
        b.tvTipDesc.text  = tip.description
        b.tvTipCategory.text = tip.category.name

        try {
            val color = Color.parseColor(tip.colorHex)
            b.thumbBackground.setBackgroundColor(
                Color.argb(30, Color.red(color), Color.green(color), Color.blue(color))
            )
            b.tvTipCategory.setTextColor(color)
        } catch (_: Exception) {}
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<VetTip>() {
            override fun areItemsTheSame(a: VetTip, b: VetTip) = a.id == b.id
            override fun areContentsTheSame(a: VetTip, b: VetTip) = a == b
        }
    }
}
