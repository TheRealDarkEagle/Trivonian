package com.example.trivonian.gameFragment

import androidx.lifecycle.ViewModel
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository

class GameFragmentViewModel : ViewModel() {


    private val repository = QuestionRepository
    lateinit var question: Question

    var questionText: String = ""
    lateinit var possibleAnswers: List<String>


    init {
        repository.resetGame()
        setup()
    }

    private fun setup() {
        question = repository.getQuestion()
        questionText = question.questionText
        populatePossibleAnswers()

    }

    private fun populatePossibleAnswers() {
        val answerList = mutableListOf<String>()
        answerList.add(question.correctAnswer)
        question.incorrectAnswer.map {
            answerList.add(it)
        }

        answerList.shuffle()
        possibleAnswers  = answerList

    }

    fun questionAnswered(answer: String) {
        repository.saveAnswer(answer)
    }

    fun hasNewQuestion(): Boolean {
        return checkForNewQuestion()
    }

    fun getNewQuestion() {
        setup()
    }

    private fun checkForNewQuestion(): Boolean {
        return repository.isAnotherQuestion()
    }

}