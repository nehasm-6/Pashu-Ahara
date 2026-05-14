package com.pashuaahar.data.model

enum class IngredientType { GRAIN, PROTEIN, SUPPLEMENT }

data class FeedIngredient(
    val id: String,
    val name: String,
    val localName: String,
    val emoji: String,
    val colorHex: String,
    val type: IngredientType,
    val cpPct: Float,       // Crude Protein %
    val energyMJ: Float,    // Metabolisable Energy MJ/kg
    val costPerKg: Float,   // ₹ per kg
    val maxKgPerDay: Float  // safety cap
)
