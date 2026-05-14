package com.pashuaahar.data.model

data class RecipeItem(
    val ingredient: FeedIngredient,
    val quantityKg: Float
) {
    val costForItem: Float
        get() = quantityKg * ingredient.costPerKg

    val displayQuantity: String
        get() = if (quantityKg < 1f) "${(quantityKg * 1000).toInt()}g" else "${quantityKg}kg"
}

data class FeedRecipe(
    val items: List<RecipeItem>,
    val totalCostPerDay: Float,
    val marketCostPerDay: Float,
    val totalCpKg: Float,
    val targetCpKg: Float,
    val totalMeMJ: Float,
    val targetMeMJ: Float
) {
    val savingsPerDay: Float get() = marketCostPerDay - totalCostPerDay
    val savingsPct: Int
        get() = if (marketCostPerDay > 0)
            ((savingsPerDay / marketCostPerDay) * 100).toInt() else 0
    val cpCoveragePct: Int
        get() = minOf(100, ((totalCpKg / targetCpKg) * 100).toInt())
    val meCoveragePct: Int
        get() = minOf(100, ((totalMeMJ / targetMeMJ) * 100).toInt())
}
