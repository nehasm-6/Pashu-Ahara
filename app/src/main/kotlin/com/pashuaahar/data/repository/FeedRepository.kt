package com.pashuaahar.data.repository

import com.pashuaahar.data.model.*

object FeedRepository {

    private val breedFactors = mapOf(
        "jersey" to BreedFactors(0.065f, 38f, 0.055f, 5.0f, 120f),
        "desi"   to BreedFactors(0.055f, 32f, 0.045f, 4.5f, 90f)
    )

    data class BreedFactors(
        val maintenanceCpPerKg: Float,
        val maintenanceMJ: Float,
        val milkCpPerL: Float,
        val milkMJPerL: Float,
        val marketBaseCostPerDay: Float
    )

    fun getBreedFactors(breed: String) =
        breedFactors[breed.lowercase()] ?: breedFactors["jersey"]!!

    val ingredients = listOf(
        FeedIngredient("maize",           "Maize",           "Makka",              "🌽", "#F59E0B", IngredientType.GRAIN,       9.0f,  13.8f, 18f, 3.0f),
        FeedIngredient("cottonseed_cake", "Cottonseed Cake", "Binola Khal",        "🌿", "#84CC16", IngredientType.PROTEIN,    36.0f,  11.0f, 28f, 2.0f),
        FeedIngredient("wheat_bran",      "Wheat Bran",      "Chokar",             "🌾", "#D97706", IngredientType.GRAIN,      15.5f,  11.5f, 14f, 2.0f),
        FeedIngredient("groundnut_cake",  "Groundnut Cake",  "Moongfali Khal",     "🥜", "#B45309", IngredientType.PROTEIN,    45.0f,  12.5f, 35f, 1.5f),
        FeedIngredient("soybean_meal",    "Soybean Meal",    "Soya Khal",          "🫘", "#10B981", IngredientType.PROTEIN,    44.0f,  12.8f, 40f, 1.5f),
        FeedIngredient("rice_bran",       "Rice Bran",       "Chawal Ki Bhoosi",   "🍚", "#FCD34D", IngredientType.GRAIN,      12.0f,  11.0f, 10f, 1.5f),
        FeedIngredient("mineral_mix",     "Mineral Mix",     "Khanij Mishran",     "💊", "#6366F1", IngredientType.SUPPLEMENT,  0.0f,   0.0f, 60f, 0.05f)
    )

    val vetTips = listOf(
        VetTip(1,  TipCategory.HYGIENE, "🧹", "Daily Shed Cleaning",         "Clean the shed at least twice a day. Remove dung and wet bedding. Unhygienic sheds cause mastitis and reduce milk yield by up to 15%.",                              "#3B82F6", true),
        VetTip(2,  TipCategory.HYGIENE, "🚿", "Udder Washing Before Milking","Always wash udders with clean lukewarm water before and after milking. This prevents bacterial contamination.",                                                         "#6366F1", false),
        VetTip(3,  TipCategory.FODDER,  "🌿", "Green Fodder Storage",        "Store green fodder in a shaded, well-ventilated area. Never feed wilted or mouldy fodder — it can cause bloating.",                                                    "#10B981", true),
        VetTip(4,  TipCategory.FODDER,  "💧", "Fresh Water Always",          "A cow producing 10L of milk needs at least 50L of clean water per day. Keep troughs clean and full at all times.",                                                     "#0EA5E9", false),
        VetTip(5,  TipCategory.HEALTH,  "💉", "Vaccination Schedule",        "Vaccinate against FMD, HS, and BQ annually. Keep a vaccination diary. A sick cow stops producing milk entirely.",                                                      "#EF4444", true),
        VetTip(6,  TipCategory.HEALTH,  "🦷", "Deworming Every 3 Months",    "Internal parasites silently steal nutrition. Deworm every 3 months for significantly improved feed conversion.",                                                       "#F59E0B", false),
        VetTip(7,  TipCategory.MILKING, "⏰", "Consistent Milking Times",    "Milk at the same time every day. Cows have a body clock — inconsistency reduces yield by up to 10%.",                                                                  "#8B5CF6", true),
        VetTip(8,  TipCategory.MILKING, "🤲", "Proper Milking Technique",    "Use the full-hand squeeze method. Never pull the teat. Proper technique prevents teat damage and ensures complete udder emptying.",                                   "#EC4899", false),
        VetTip(9,  TipCategory.FODDER,  "🌾", "Dry Fodder Ratio",            "Maintain 60% green and 40% dry fodder. Excess green fodder causes loose dung and reduces fat content in milk.",                                                       "#84CC16", false),
        VetTip(10, TipCategory.HYGIENE, "🐝", "Fly & Pest Control",          "Use neem-based sprays in the shed. Flies spread diseases and cause stress that directly reduces milk production.",                                                     "#F97316", false),
        VetTip(11, TipCategory.HEALTH,  "🌡️", "Summer Heat Management",      "Provide shade and use water misting in summer. Heat stress above 27°C can reduce milk yield by 25%.",                                                                "#06B6D4", true),
        VetTip(12, TipCategory.MILKING, "📊", "Track Milk Records Daily",    "Write down milk yield daily. Sudden drops often signal illness 3-4 days before visible symptoms, allowing early treatment.",                                          "#14B8A6", false)
    )
}
