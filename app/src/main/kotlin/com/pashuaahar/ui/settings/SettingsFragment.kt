package com.pashuaahar.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pashuaahar.databinding.FragmentSettingsBinding
import com.pashuaahar.utils.LocaleHelper

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnglish.setOnClickListener {
            LocaleHelper.setLocale("en")
        }

        binding.btnKannada.setOnClickListener {
            LocaleHelper.setLocale("kn")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
