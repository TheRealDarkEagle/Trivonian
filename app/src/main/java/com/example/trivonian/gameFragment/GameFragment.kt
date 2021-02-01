package com.example.trivonian.gameFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.trivonian.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private val viewModel by activityViewModels<GameFragmentViewModel>()
    private val binding: FragmentGameBinding by lazy {
        FragmentGameBinding.inflate(layoutInflater, null, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupQuestion()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameFragmentNextButton.setOnClickListener {
            questionAnswered()
        }
    }

    private fun setupQuestion() {
        updateQuestion()
        createRadioButtons()
    }

    private fun questionAnswered() {
        registerAnswer()
        if(viewModel.hasNewQuestion()) {
            viewModel.getNewQuestion()
            setupQuestion()
        } else {
            directToResultFragment()
        }
    }

    private fun registerAnswer() {
        val radioGroup = binding.radioGroup
        for (view in radioGroup.children) {
            val button = view as RadioButton
            if (button.isChecked) {
                viewModel.questionAnswered(button.text.toString())
            }
        }
    }

    private fun directToResultFragment() {
        val action = GameFragmentDirections.actionGameFragmentToResultFragment()
        findNavController(this).navigate(action)


    }

    private fun createRadioButtons() {
        val radioGroup = binding.radioGroup
        radioGroup.removeAllViews()
        for (answer in viewModel.possibleAnswers) {
            radioGroup.addView(customizeRadioButton(answer))
        }
    }

    private fun customizeRadioButton(answer: String): RadioButton {
        val radioButton = RadioButton(this.requireContext())
        radioButton.text = answer
        radioButton.id = View.generateViewId()
        radioButton.textSize = 25f
        return radioButton
    }

    private fun updateQuestion() {
        binding.question = viewModel.question
    }

}