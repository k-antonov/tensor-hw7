package com.example.tensorhw7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tensorhw7.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toAnimationsButton.setOnClickListener {
            replaceWith(AnimationsFragment())
        }
    }

    private fun replaceWith(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .setCustomAnimations(R.anim.up_in, R.anim.down_out, R.anim.up_in, R.anim.down_out)
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }
}