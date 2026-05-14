package com.pashuaahar.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pashuaahar.databinding.FragmentRecipeBinding
import com.pashuaahar.viewmodel.CowViewModel

class RecipeFragment : Fragment() {
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private val vm: CowViewModel by activityViewModels()
    private val adapter = RecipeAdapter()

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentRecipeBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvIngredients.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIngredients.adapter = adapter

        vm.recipe.observe(viewLifecycleOwner) { recipe ->
            if (recipe == null) return@observe
            binding.emptyState.visibility = View.GONE
            binding.contentGroup.visibility = View.VISIBLE

            val profile = vm.profile.value
            binding.tvRecipeSubtitle.text =
                "Optimised for ${recipe.items.sumOf { it.quantityKg.toDouble() }.let { "%.1f".format(it) }}kg/day · ${profile?.breed ?: ""} cow"

            binding.tvStatYield.text  = "${profile?.targetYieldL ?: "—"}L"
            binding.tvStatCost.text   = "₹${recipe.totalCostPerDay.toInt()}"
            binding.tvStatSavings.text = "${recipe.savingsPct}%"

            // Live slider
            val targetL = profile?.targetYieldL ?: 10f
            binding.seekLiveYield.progress = (targetL * 2).toInt()
            binding.tvLiveYieldVal.text    = "${targetL}L / day"

            // Nutrition bars
            binding.progressCp.progress = recipe.cpCoveragePct
            binding.tvCpText.text = "${"%.2f".format(recipe.totalCpKg)} / ${"%.2f".format(recipe.targetCpKg)} kg CP"
            binding.progressMe.progress = recipe.meCoveragePct
            binding.tvMeText.text = "${"%.1f".format(recipe.totalMeMJ)} / ${"%.1f".format(recipe.targetMeMJ)} MJ ME"

            adapter.submitList(recipe.items)
        }

        binding.seekLiveYield.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val yieldL = progress / 2f
                binding.tvLiveYieldVal.text = "${yieldL}L / day"
                if (fromUser) vm.recalculate(yieldL)
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
