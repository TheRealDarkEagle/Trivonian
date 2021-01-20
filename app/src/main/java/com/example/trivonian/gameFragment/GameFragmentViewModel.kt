package com.example.trivonian.gameFragment

import androidx.lifecycle.ViewModel
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository

class GameFragmentViewModel : ViewModel() {


    //private val repository = QuestionRepository
    private lateinit var question: Question

    var questionText: String = ""
    lateinit var possibleAnswers: List<String>


    init {
        QuestionRepository.resetGame()
        setup()
    }

    private fun setup() {
        question = QuestionRepository.getQuestion()
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

    fun questionAnswered(answer: String) {
        QuestionRepository.saveAnswer(answer)
    }

    fun hasNewQuestion(): Boolean {
        return checkForNewQuestion()
    }

    fun getNewQuestion() {
        setup()
    }

    private fun checkForNewQuestion(): Boolean {
        return QuestionRepository.isAnotherQuestion()
    }

}