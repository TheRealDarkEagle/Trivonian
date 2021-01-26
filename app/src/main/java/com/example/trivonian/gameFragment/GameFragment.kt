package com.example.trivonian.gameFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        updateAnswers()
    }

    private fun questionAnswered() {
        registerAnswer()
        if (viewModel.hasNewQuestion()) {
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

    private fun updateAnswers() {

        val radioGroup = binding.radioGroup
        radioGroup.removeAllViews()
        for (answer in viewModel.possibleAnswers) {
            val radioButton = RadioButton(this.requireContext())
            radioButton.text = answer
            radioButton.id = View.generateViewId()
            radioButton.textSize = 25f

            radioGroup.addView(radioButton)
        }
    }

    private fun updateQuestion() {
        binding.question = viewModel.question
    }
}