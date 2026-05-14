package com.pashuaahar.data.model

enum class TipCategory { ALL, HYGIENE, FODDER, HEALTH, MILKING }

data class VetTip(
    val id: Int,
    val category: TipCategory,
    val emoji: String,
    val title: String,
    val description: String,
    val colorHex: String,
    val hasVideo: Boolean
)
