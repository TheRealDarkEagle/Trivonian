package com.example.trivonian.gameFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.trivonian.R

class GameFragment : Fragment() {
    private lateinit var viewModel: GameFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(GameFragmentViewModel::class.java)

        super.onViewCreated(view, savedInstanceState)
        val questionTextView = view.findViewById<TextView>(R.id.gameFragment_question)
        questionTextView.text = viewModel.question.questionText

        val buttonOne = view.findViewById<RadioButton>(R.id.radioButton1)
        buttonOne.text = viewModel.question.answers[0]
        val buttonTwo = view.findViewById<RadioButton>(R.id.radioButton2)
        buttonTwo.text = viewModel.question.answers[1]
        val buttonThree = view.findViewById<RadioButton>(R.id.radioButton3)
        buttonThree.text = viewModel.question.answers[2]
        val buttonFour = view.findViewById<RadioButton>(R.id.radioButton4)
        buttonFour.text = viewModel.question.correctAnswer

        val radioButtons = listOf<RadioButton>(buttonOne, buttonTwo, buttonThree, buttonFour)


        view.findViewById<Button>(R.id.gameFragment_nextButton).setOnClickListener {
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
        val action = GameFragmentDirections.actionGameFragmentToResultFragment(viewModel.question.questionText, viewModel.getUserAnswer(), viewModel.question.correctAnswer)
        view?.findNavController()?.navigate(action)
    }
}