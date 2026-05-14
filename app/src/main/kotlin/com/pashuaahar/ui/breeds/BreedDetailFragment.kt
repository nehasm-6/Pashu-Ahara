package com.pashuaahar.ui.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.pashuaahar.databinding.FragmentBreedDetailBinding
import com.pashuaahar.viewmodel.CowViewModel

class BreedDetailFragment : Fragment() {

    private var _binding: FragmentBreedDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CowViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val breedId = arguments?.getString("breedId")
        
        viewModel.breeds.observe(viewLifecycleOwner) { breeds ->
            val breed = breeds.find { it.id == breedId }
            breed?.let {
                binding.detailName.text = it.name
                binding.detailOrigin.text = it.origin
                binding.detailMilkCapacity.text = it.milkCapacity
                binding.detailDescription.text = it.description
                
                Glide.with(requireContext())
                    .load(it.imageUrl)
                    .into(binding.detailImage)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
