package com.pashuaahar.utils

import com.pashuaahar.data.model.*
import com.pashuaahar.data.repository.FeedRepository
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Offline nutrition calculator.
 * All formulas based on ICAR/NRC livestock nutrition standards.
 */
object NutritionCalculator {

    fun calculate(profile: CowProfile): FeedRecipe {
        val f = FeedRepository.getBreedFactors(profile.breed)

        // Requirements
        val targetCP = f.maintenanceCpPerKg * profile.weightKg + f.milkCpPerL * profile.targetYieldL
        val targetME = f.maintenanceMJ + f.milkMJPerL * profile.targetYieldL

        var remainingCP = targetCP
        var remainingME = targetME
        val items = mutableListOf<RecipeItem>()

        // 1. Always add mineral supplement (fixed 50g)
        val mineral = FeedRepository.ingredients.first { it.type == IngredientType.SUPPLEMENT }
        items += RecipeItem(mineral, 0.05f)

        // 2. Protein sources — sorted by CP% descending
        val proteins = FeedRepository.ingredients
            .filter { it.type == IngredientType.PROTEIN }
            .sortedByDescending { it.cpPct }

        for (src in proteins) {
            if (remainingCP <= 0.01f) break
            val kgForCP = remainingCP / (src.cpPct / 100f)
            val qty = roundToQuarter(min(kgForCP, src.maxKgPerDay))
            if (qty < 0.05f) continue
            items += RecipeItem(src, qty)
            remainingCP -= qty * (src.cpPct / 100f)
            remainingME -= qty * src.energyMJ
        }

        // 3. Energy/grain sources — sorted by energy density descending
        val grains = FeedRepository.ingredients
            .filter { it.type == IngredientType.GRAIN }
            .sortedByDescending { it.energyMJ }

        for (src in grains) {
            if (remainingME <= 0.5f) break
            val kgForME = remainingME / src.energyMJ
            val qty = roundToQuarter(min(kgForME, src.maxKgPerDay))
            if (qty < 0.1f) continue
            items += RecipeItem(src, qty)
            remainingCP -= qty * (src.cpPct / 100f)
            remainingME -= qty * src.energyMJ
        }

        // Totals
        val totalCost = items.sumOf { it.costForItem.toDouble() }.toFloat()
        val totalCP   = items.sumOf { (it.quantityKg * it.ingredient.cpPct / 100).toDouble() }.toFloat()
        val totalME   = items.sumOf { (it.quantityKg * it.ingredient.energyMJ).toDouble() }.toFloat()
        val marketCost = f.marketBaseCostPerDay + profile.targetYieldL * 12f

        return FeedRecipe(
            items           = items,
            totalCostPerDay = totalCost,
            marketCostPerDay = marketCost,
            totalCpKg       = totalCP,
            targetCpKg      = targetCP,
            totalMeMJ       = totalME,
            targetMeMJ      = targetME
        )
    }

    private fun roundToQuarter(value: Float): Float =
        ((value * 4).roundToInt() / 4f)
}
