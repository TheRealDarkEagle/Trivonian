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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.trivonian.R
import com.example.trivonian.databinding.FragmentGameBinding
import com.example.trivonian.dataclasses.GameState
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.util.logger.Logable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameFragment : Fragment(), Logable {

    private lateinit var viewModel: GameFragmentViewModel
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel = ViewModelProvider(this).get(GameFragmentViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameFragmentNextButton.setOnClickListener {
            registerAnswer()
            if(viewModel.gameState.value == GameState.FINISH) {
                directToResultFragment()
            }

        }
        lifecycleScope.launchWhenCreated {
            logInformation("launched lifescycle scope")
            viewModel.question.collect {
                logInformation("got a hot question dude")
                if (it != null) {
                    binding.question = it
                    logInformation("send the question to binding")
                    updateAnswers(createAnswerList(it))

                }
            }
        }
    }

    private fun createAnswerList(it: Question): List<String> {
        val answers = mutableListOf(it.correctAnswer)
        answers.addAll(it.incorrectAnswers)
        answers.shuffle()
        return answers
    }

    private fun registerAnswer() {
        val radioGroup = binding.radioGroup
        for (view in radioGroup.children) {
            val button = view as RadioButton
            if (button.isChecked) {
                viewModel.questionAnswered(button.text.toString())

                if (viewModel.gameState.value == GameState.FINISH) {
                    directToResultFragment()
                } else {
                    viewModel.getNewQuestion()
                }
            }
        }
    }

    private fun directToResultFragment() {
        val action = GameFragmentDirections.actionGameFragmentToResultFragment()
        findNavController(this).navigate(action)
    }

    private fun updateAnswers(answers: List<String>) {
        val radioGroup = binding.radioGroup
        radioGroup.removeAllViews()
        for (answer in answers) {
            val radioButton = RadioButton(this.requireContext())
            radioButton.text = answer
            radioButton.id = View.generateViewId()
            radioButton.textSize = 25f

            radioGroup.addView(radioButton)
        }
    }

}