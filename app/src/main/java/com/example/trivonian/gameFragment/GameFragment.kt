package com.example.trivonian.gameFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
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
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(GameFragmentViewModel::class.java)

        val questionTextView = binding.gameFragmentQuestion
        questionTextView.text = viewModel.getQuestionText()

        val answers = viewModel.getAnswers()

        val buttonOne = binding.radioButton1
        buttonOne.text = answers[0]
        val buttonTwo = binding.radioButton2
        buttonTwo.text = answers[1]
        val buttonThree = binding.radioButton3
        buttonThree.text = answers[2]
        val buttonFour = binding.radioButton4
        buttonFour.text = answers[3]

        val radioButtons = listOf(buttonOne, buttonTwo, buttonThree, buttonFour)


        binding.gameFragmentNextButton.setOnClickListener {
            for (radioButton in radioButtons) {
                if (radioButton.isChecked) {
                    viewModel.setUserAnswer(radioButton.text.toString())
                    break
                }
            }
            directToResultFragment()
        }
    }

    private fun directToResultFragment() {
        val action = GameFragmentDirections.actionGameFragmentToResultFragment(
            viewModel.getQuestionText(),
            viewModel.getUserAnswer(),
            viewModel.getCorrectAnswer()
        )
        findNavController(this).navigate(action)
    }
}