package com.pashuaahar.ui.tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.pashuaahar.R
import com.pashuaahar.data.model.TipCategory
import com.pashuaahar.databinding.FragmentTipsBinding
import com.pashuaahar.viewmodel.CowViewModel

class TipsFragment : Fragment() {
    private var _binding: FragmentTipsBinding? = null
    private val binding get() = _binding!!
    private val vm: CowViewModel by activityViewModels()
    private val adapter = TipAdapter()

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentTipsBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTips.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvTips.adapter = adapter

        vm.tips.observe(viewLifecycleOwner) { adapter.submitList(it) }

        // Filter chips
        val chipMap = mapOf(
            R.id.chipAll     to TipCategory.ALL,
            R.id.chipHygiene to TipCategory.HYGIENE,
            R.id.chipFodder  to TipCategory.FODDER,
            R.id.chipHealth  to TipCategory.HEALTH,
            R.id.chipMilking to TipCategory.MILKING
        )
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            val id = checkedIds.firstOrNull() ?: R.id.chipAll
            vm.filterTips(chipMap[id] ?: TipCategory.ALL)
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
