package com.example.trivonian.startFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.trivonian.R
import com.example.trivonian.repository.QuestionRepository

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val playGameButton = view.findViewById<Button>(R.id.playNowButton)

        playGameButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_startFragment_to_gameFragment)
        }

    }
}