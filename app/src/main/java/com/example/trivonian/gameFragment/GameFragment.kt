package com.example.trivonian.gameFragment

import android.annotation.SuppressLint
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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
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
        updateAnswers()
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

    private fun updateQuestion() {
        binding.question = viewModel.question
    }
}