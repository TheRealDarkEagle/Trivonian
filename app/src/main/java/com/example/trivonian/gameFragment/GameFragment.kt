package com.example.trivonian.gameFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.trivonian.databinding.FragmentGameBinding
import com.example.trivonian.dataclasses.GameState
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.util.logger.Logable
import kotlinx.coroutines.flow.collect

class GameFragment : Fragment(), Logable {

    private val viewModel by activityViewModels<GameFragmentViewModel>()
    private val binding: FragmentGameBinding by lazy {
        FragmentGameBinding.inflate(layoutInflater, null, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameFragmentNextButton.setOnClickListener {
            questionAnswered()
        }
        logInformation("onViewCreated")
        lifecycleScope.launchWhenCreated {
            logInformation("launched collecting of questions from viewModel")
            viewModel.question.collect {
                logInformation("recieved question")
                binding.question = it
                updateAnswers(createAnswerList(it))
            }
        }
    }

    private fun createAnswerList(question: Question): List<String> {
        val answers = mutableListOf(question.correctAnswer)
        answers.addAll(question.incorrectAnswers)
        answers.shuffle()
        return answers
    }

    private fun questionAnswered() {
        if (registerAnswer()) {
            if (viewModel.gameState.value == GameState.RUNNING) {
                viewModel.getNewQuestion()
            } else {
                directToResultFragment()
            }
        }
    }

    private fun registerAnswer(): Boolean {
        val radioGroup = binding.radioGroup
        for (view in radioGroup.children) {
            val button = view as RadioButton
            if (button.isChecked) {
                viewModel.questionAnswered(button.text.toString())
                return true
            }
        }
        return false
    }

    private fun directToResultFragment() {
        val action = GameFragmentDirections.actionGameFragmentToResultFragment()
        findNavController(this).navigate(action)
    }

    private fun updateAnswers(answers: List<String>) {
        logInformation("updating Answers")
        val radioGroup = binding.radioGroup
        radioGroup.removeAllViews()
        for (answer in answers) {
            radioGroup.addView(createRadioButton(answer))
        }
    }

    private fun createRadioButton(answer: String): RadioButton {
        val radioButton = RadioButton(this.requireContext())
        radioButton.text = answer
        radioButton.id = View.generateViewId()
        radioButton.textSize = 25f
        return radioButton
    }
}
