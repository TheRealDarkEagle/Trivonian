package com.example.trivonian.startFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.trivonian.R
import com.example.trivonian.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private val binding: FragmentStartBinding by lazy {
        FragmentStartBinding.inflate(layoutInflater, null, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playNowButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_startFragment_to_gameFragment)
        }
    }
}