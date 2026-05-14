package com.pashuaahar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.pashuaahar.data.model.CowBreed
import com.pashuaahar.data.model.CowProfile
import com.pashuaahar.data.model.FeedRecipe
import com.pashuaahar.data.model.TipCategory
import com.pashuaahar.data.model.VetTip
import com.pashuaahar.data.repository.FeedRepository
import com.pashuaahar.utils.NutritionCalculator

class CowViewModel : ViewModel() {

    private val db = try { FirebaseFirestore.getInstance() } catch (e: Exception) { null }

    // ── Stepper State ────────────────────────────────────────
    val currentStep = MutableLiveData<Int>(0)
    val selectedBreedKey = MutableLiveData<String>("Jersey")

    // ── Profile & Data ───────────────────────────────────────
    private val _profile = MutableLiveData<CowProfile>()
    val profile: LiveData<CowProfile> = _profile

    private val _recipe = MutableLiveData<FeedRecipe>()
    val recipe: LiveData<FeedRecipe> = _recipe

    private val _tips = MutableLiveData<List<VetTip>>(FeedRepository.vetTips)
    val tips: LiveData<List<VetTip>> = _tips

    private val _breeds = MutableLiveData<List<CowBreed>>()
    val breeds: LiveData<List<CowBreed>> = _breeds

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var allBreeds = getInitialBreeds()

    init {
        _breeds.value = allBreeds
        fetchBreedsFromFirebase()
    }

    private fun fetchBreedsFromFirebase() {
        val firestore = db ?: return
        _loading.value = true
        firestore.collection("breeds").get()
            .addOnSuccessListener { result: QuerySnapshot ->
                val list = result.documents.mapNotNull { doc ->
                    doc.toObject(CowBreed::class.java)?.copy(id = doc.id)
                }
                if (list.isNotEmpty()) {
                    allBreeds = list
                    _breeds.value = list
                }
                _loading.value = false
            }
            .addOnFailureListener {
                _loading.value = false
            }
    }

    fun searchBreeds(query: String) {
        _breeds.value = if (query.isEmpty()) allBreeds
        else allBreeds.filter {
            it.name.contains(query, ignoreCase = true) ||
            it.description.contains(query, ignoreCase = true)
        }
    }

    fun filterByCategory(category: String) {
        _breeds.value = if (category == "All") allBreeds
        else allBreeds.filter { it.category == category }
    }

    private fun getInitialBreeds(): List<CowBreed> {
        return listOf(
            CowBreed("1", "Gir Cow", "Gujarat", "12-15L", "Famous for high milk yield.", "https://via.placeholder.com/300?text=Gir", "Indian"),
            CowBreed("2", "Hallikar", "Karnataka", "4-6L", "Draught breed.", "https://via.placeholder.com/300?text=Hallikar", "Indian"),
            CowBreed("3", "Amrit Mahal", "Karnataka", "3-5L", "Endurance breed.", "https://via.placeholder.com/300?text=Amrit+Mahal", "Indian"),
            CowBreed("4", "Jersey", "Jersey", "20-25L", "High butterfat.", "https://via.placeholder.com/300?text=Jersey", "Foreign"),
            CowBreed("5", "Sahiwal", "Punjab", "10-12L", "Top dairy breed.", "https://via.placeholder.com/300?text=Sahiwal", "Indian")
        )
    }

    fun saveProfile(profile: CowProfile) {
        _profile.value = profile
        recalculate(profile.targetYieldL)
    }

    fun recalculate(targetYieldL: Float) {
        val current = _profile.value ?: return
        val updated = current.copy(targetYieldL = targetYieldL)
        _profile.value = updated
        _recipe.value = NutritionCalculator.calculate(updated)
    }

    fun filterTips(category: TipCategory) {
        _tips.value = if (category == TipCategory.ALL) FeedRepository.vetTips
        else FeedRepository.vetTips.filter { it.category == category }
    }

    fun getMonthlySavingsHistory(): List<Triple<String, Float, Float>> {
        val recipe = _recipe.value ?: return emptyList()
        val months = listOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec")
        return months.mapIndexed { i, m ->
            val v = 1f + (i % 3) * 0.05f - 0.05f
            Triple(m, recipe.totalCostPerDay * 30 * v, recipe.marketCostPerDay * 30 * v)
        }
    }
}
