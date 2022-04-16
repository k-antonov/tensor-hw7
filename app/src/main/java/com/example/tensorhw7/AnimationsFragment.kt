package com.example.tensorhw7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tensorhw7.databinding.FragmentAnimationsBinding


class AnimationsFragment : Fragment() {
    private lateinit var binding: FragmentAnimationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimationsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}