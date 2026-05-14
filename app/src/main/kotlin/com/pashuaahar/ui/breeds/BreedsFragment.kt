package com.pashuaahar.ui.breeds

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pashuaahar.R
import com.pashuaahar.databinding.FragmentBreedsBinding
import com.pashuaahar.viewmodel.CowViewModel

class BreedsFragment : Fragment() {

    private var _binding: FragmentBreedsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CowViewModel by activityViewModels()
    private lateinit var adapter: BreedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()
        setupFilters()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = BreedsAdapter { breed ->
            val bundle = Bundle().apply {
                putString("breedId", breed.id)
            }
            findNavController().navigate(R.id.action_breeds_to_details, bundle)
        }
        binding.breedsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.breedsRecyclerView.adapter = adapter
    }

    private fun setupSearch() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchBreeds(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupFilters() {
        binding.filterGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            when (checkedIds.firstOrNull()) {
                R.id.chipAll -> viewModel.filterByCategory("All")
                R.id.chipIndian -> viewModel.filterByCategory("Indian")
                R.id.chipForeign -> viewModel.filterByCategory("Foreign")
            }
        }
    }

    private fun observeViewModel() {
        viewModel.breeds.observe(viewLifecycleOwner) { breeds ->
            adapter.submitList(breeds)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading == true) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
