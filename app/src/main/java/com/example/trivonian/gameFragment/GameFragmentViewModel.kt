package com.example.trivonian.gameFragment

import androidx.lifecycle.ViewModel
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository

class GameFragmentViewModel : ViewModel() {

    private val repository = QuestionRepository()
    private var userAnswer: String = ""
    var question: Question
    var answers: List<String>

    init {
        question = repository.getQuestion()
        val allAnswers = mutableListOf(question.correctAnswer)
        question.incorrectAnswer.map { allAnswers.add(it) }
        val answerList = allAnswers.toList()
        answers = answerList
    }

    fun setUserAnswer(answer: String) {
        repository.saveAnswer(answer)
    }
    fun getUserAnswer(): String {
        return repository.getUserAnswer()
    }




}