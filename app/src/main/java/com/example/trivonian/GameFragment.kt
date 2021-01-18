package com.example.trivonian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import org.w3c.dom.Text

class GameFragment : Fragment() {

    data class Question(val questionText: String, val answers: List<String>, val correctAnswer: String)

    val question = Question("In the Kingdom Heart series who provides the english voice for Master Eraqus?",
        listOf("Mark Hamill", "Jason Dohring", "Jesse McCartney", "Haley Joel Osment"),"Mark Hamill")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionTextView = view.findViewById<TextView>(R.id.gameFragment_question)
        questionTextView.text = question.questionText

        val buttonOne = view.findViewById<RadioButton>(R.id.radioButton1)
        buttonOne.text = question.answers.get(0)
        val buttonTwo = view.findViewById<RadioButton>(R.id.radioButton2)
        buttonTwo.text = question.answers.get(1)
        val buttonThree = view.findViewById<RadioButton>(R.id.radioButton3)
        buttonThree.text = question.answers.get(2)
        val buttonFour = view.findViewById<RadioButton>(R.id.radioButton4)
        buttonFour.text = question.answers.get(3)
    }
}