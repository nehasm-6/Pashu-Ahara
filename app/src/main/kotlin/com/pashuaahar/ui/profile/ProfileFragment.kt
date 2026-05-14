package com.pashuaahar.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pashuaahar.R
import com.pashuaahar.data.model.CowProfile
import com.pashuaahar.databinding.FragmentProfileBinding
import com.pashuaahar.viewmodel.CowViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val vm: CowViewModel by activityViewModels()

    private val totalSteps = 5

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupBreedRecyclerView()
        setupSliders()
        setupButtons()
        
        vm.currentStep.observe(viewLifecycleOwner) { step ->
            showStep(step)
        }

        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }
    }

    private fun setupBreedRecyclerView() {
        val breedList = listOf(
            BreedUIModel("Jersey", R.string.breed_jersey, R.string.jersey_sub, "https://upload.wikimedia.org/wikipedia/commons/e/e1/Jersey_cow.jpg"),
            BreedUIModel("Desi", R.string.breed_desi, R.string.desi_sub, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Gir_cow.jpg/320px-Gir_cow.jpg"),
            BreedUIModel("Gir", R.string.breed_gir, R.string.gir_sub, "https://upload.wikimedia.org/wikipedia/commons/0/05/Gir_cow.jpg"),
            BreedUIModel("Hallikar", R.string.breed_hallikar, R.string.hallikar_sub, "https://upload.wikimedia.org/wikipedia/commons/6/64/Hallikar_bull.jpg"),
            BreedUIModel("Amrit Mahal", R.string.breed_amrit_mahal, R.string.amrit_mahal_sub, "https://upload.wikimedia.org/wikipedia/commons/8/82/Amrit_Mahal_cattle.jpg"),
            BreedUIModel("Ongole", R.string.breed_ongole, R.string.ongole_sub, "https://upload.wikimedia.org/wikipedia/commons/1/1a/Ongole_bull.jpg"),
            BreedUIModel("Sahiwal", R.string.breed_sahiwal, R.string.sahiwal_sub, "https://upload.wikimedia.org/wikipedia/commons/1/1a/Sahiwal_Cow.jpg"),
            BreedUIModel("HF", R.string.breed_hf, R.string.hf_sub, "https://upload.wikimedia.org/wikipedia/commons/a/a8/Holstein_Dairy_Cow_at_Fair.jpg"),
            BreedUIModel("Deoni", R.string.breed_deoni, R.string.deoni_sub, "https://upload.wikimedia.org/wikipedia/commons/4/4c/Deoni_Cow.jpg"),
            BreedUIModel("Red Sindhi", R.string.breed_red_sindhi, R.string.red_sindhi_sub, "https://upload.wikimedia.org/wikipedia/commons/9/9c/Red_Sindhi_Cow.jpg"),
            BreedUIModel("Kangayam", R.string.breed_kangayam, R.string.kangayam_sub, "https://upload.wikimedia.org/wikipedia/commons/8/8b/Kangayam_bull.jpg")
        )

        val currentSelection = vm.selectedBreedKey.value
        breedList.forEach { it.isSelected = it.nameKey == currentSelection }

        val adapter = BreedSelectionAdapter(breedList) { breedKey ->
            vm.selectedBreedKey.value = breedKey
        }
        binding.rvBreeds.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvBreeds.adapter = adapter
        binding.rvBreeds.setHasFixedSize(true)
        binding.rvBreeds.isNestedScrollingEnabled = false
    }

    private fun setupSliders() {
        binding.seekCurrentYield.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvCurrentYieldVal.text = "${progress / 2.0}L"
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })
        binding.seekTargetYield.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvTargetYieldVal.text = "${progress / 2.0}L"
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })
        binding.seekCurrentYield.progress = 12
        binding.seekTargetYield.progress  = 20
    }

    private fun setupButtons() {
        binding.stepButtons.btnNext.setOnClickListener {
            val step = vm.currentStep.value ?: 0
            if (validateCurrentStep(step)) {
                if (step < totalSteps - 1) vm.currentStep.value = step + 1
                else generateRecipe()
            }
        }
        binding.stepButtons.btnBack.setOnClickListener {
            val step = vm.currentStep.value ?: 0
            if (step > 0) vm.currentStep.value = step - 1
        }
    }

    private fun showStep(step: Int) {
        val steps = listOf(
            binding.stepBreed, binding.stepAge,
            binding.stepWeight, binding.stepCurrentYield, binding.stepTargetYield
        )
        steps.forEachIndexed { i, v -> v.visibility = if (i == step) View.VISIBLE else View.GONE }
        binding.stepButtons.btnBack.visibility = if (step == 0) View.INVISIBLE else View.VISIBLE
        binding.stepButtons.btnNext.text = if (step == totalSteps - 1) getString(R.string.generate_recipe) else getString(R.string.next)
        updateProgressDots(step)
    }

    private fun updateProgressDots(currentStep: Int) {
        val dots = listOf(binding.dot1, binding.dot2, binding.dot3, binding.dot4, binding.dot5)
        dots.forEachIndexed { i, dot ->
            when {
                i < currentStep  -> dot.setBackgroundResource(R.drawable.dot_done)
                i == currentStep -> dot.setBackgroundResource(R.drawable.dot_active)
                else             -> dot.setBackgroundResource(R.drawable.dot_inactive)
            }
        }
    }

    private fun validateCurrentStep(step: Int): Boolean {
        return when (step) {
            1 -> {
                val age = binding.etAge.text.toString().toIntOrNull()
                if (age == null || age < 1 || age > 20) {
                    Toast.makeText(context, getString(R.string.invalid_age), Toast.LENGTH_SHORT).show(); false
                } else true
            }
            2 -> {
                val wt = binding.etWeight.text.toString().toFloatOrNull()
                if (wt == null || wt < 100 || wt > 800) {
                    Toast.makeText(context, getString(R.string.invalid_weight), Toast.LENGTH_SHORT).show(); false
                } else true
            }
            else -> true
        }
    }

    private fun generateRecipe() {
        val profile = CowProfile(
            breed         = vm.selectedBreedKey.value ?: "Jersey",
            ageYears      = binding.etAge.text.toString().toIntOrNull() ?: 4,
            weightKg      = binding.etWeight.text.toString().toFloatOrNull() ?: 350f,
            currentYieldL = binding.seekCurrentYield.progress / 2f,
            targetYieldL  = binding.seekTargetYield.progress / 2f
        )
        vm.saveProfile(profile)
        findNavController().navigate(R.id.action_profile_to_recipe)
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
