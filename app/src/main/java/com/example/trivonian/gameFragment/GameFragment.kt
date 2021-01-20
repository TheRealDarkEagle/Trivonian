package com.example.trivonian.gameFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.trivonian.R
import com.example.trivonian.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var viewModel: GameFragmentViewModel
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel = ViewModelProvider(this).get(GameFragmentViewModel::class.java)
        updateQuestion()
        updateAnswers()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameFragmentNextButton.setOnClickListener {
            registerAnswer()
            directToResultFragment()
        }
    }

    private fun registerAnswer() {
        val radioGroup = binding.radioGroup
        for (view in radioGroup.children) {
            val button = view as RadioButton
            if (button.isChecked) {
                viewModel.userAnswer = button.text.toString()
            }
        }
    }

    private fun directToResultFragment() {
        val action = GameFragmentDirections.actionGameFragmentToResultFragment()
        findNavController(this).navigate(action)


    }

    private fun updateAnswers() {
        val radioGroup = binding.radioGroup
        for (answer in viewModel.possibleAnswers) {
            val radioButton = RadioButton(this.requireContext())
            radioButton.text = answer
            radioButton.id = View.generateViewId()
            radioButton.textSize = 25f
            
            radioGroup.addView(radioButton)
        }
    }
    private fun updateQuestion() {
        binding.gameFragmentQuestion.text = viewModel.questionText
    }
}