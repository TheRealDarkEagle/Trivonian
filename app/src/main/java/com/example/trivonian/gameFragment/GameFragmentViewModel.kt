package com.example.trivonian.gameFragment

import androidx.lifecycle.ViewModel
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository

class GameFragmentViewModel : ViewModel() {

    private val repository = QuestionRepository()
    private var question: Question
    lateinit var possibleAnswers: List<String>

    var questionText: String = ""
    var userAnswer = ""

    init {
        question = repository.getQuestion()
        questionText = question.questionText
        populatePossibleAnswers()
    }

    private fun populatePossibleAnswers() {
        val answerList = mutableListOf<String>()
        for (answer in question.answers) {
            answerList.add(answer)
        }
        answerList.add(question.correctAnswer)
        answerList.shuffle()
        possibleAnswers  = answerList

    }


}