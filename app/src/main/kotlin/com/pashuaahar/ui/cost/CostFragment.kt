package com.pashuaahar.ui.cost

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.pashuaahar.databinding.FragmentCostBinding
import com.pashuaahar.viewmodel.CowViewModel

class CostFragment : Fragment() {
    private var _binding: FragmentCostBinding? = null
    private val binding get() = _binding!!
    private val vm: CowViewModel by activityViewModels()

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentCostBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.recipe.observe(viewLifecycleOwner) { recipe ->
            if (recipe == null) { binding.emptyState.visibility = View.VISIBLE; binding.contentGroup.visibility = View.GONE; return@observe }
            binding.emptyState.visibility = View.GONE
            binding.contentGroup.visibility = View.VISIBLE

            binding.tvMonthlySavings.text = "₹${(recipe.savingsPerDay * 30).toInt()}"
            binding.tvSavingsPct.text = "vs. Market Feed — ${recipe.savingsPct}% cheaper"
            binding.tvHomeCostDay.text = "₹${recipe.totalCostPerDay.toInt()}"
            binding.tvMarketCostDay.text = "₹${recipe.marketCostPerDay.toInt()}"

            renderChart()
        }
    }

    private fun renderChart() {
        val history = vm.getMonthlySavingsHistory()
        val labels  = history.map { it.first }
        val homeEntries   = history.mapIndexed { i, t -> BarEntry(i.toFloat(), t.second) }
        val marketEntries = history.mapIndexed { i, t -> BarEntry(i.toFloat(), t.third) }

        val homeDs   = BarDataSet(homeEntries,   "Home-made").apply { color = Color.parseColor("#52B788"); setDrawValues(false) }
        val marketDs = BarDataSet(marketEntries, "Market").apply    { color = Color.parseColor("#F59E0B"); setDrawValues(false) }

        val barData = BarData(homeDs, marketDs).apply {
            barWidth = 0.35f
            groupBars(0f, 0.08f, 0.02f)
        }

        binding.barChart.apply {
            data = barData
            xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(labels)
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                setCenterAxisLabels(true)
                setDrawGridLines(false)
                textSize = 10f
            }
            axisLeft.apply { setDrawGridLines(true); gridColor = Color.parseColor("#D8F3DC") }
            axisRight.isEnabled = false
            legend.isEnabled = false
            description.isEnabled = false
            setFitBars(true)
            animateY(800)
            invalidate()
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
