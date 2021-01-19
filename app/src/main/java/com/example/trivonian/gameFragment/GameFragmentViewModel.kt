package com.example.trivonian.gameFragment

import androidx.lifecycle.ViewModel
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository

class GameFragmentViewModel : ViewModel() {

    private val repository = QuestionRepository()
    private var userAnswer: String = ""
    var question: Question

    init {
        question = repository.getQuestion()
    }

    fun setUserAnswer(answer: String) {
        repository.saveAnswer(answer)
        userAnswer = answer
    }
    fun getUserAnswer(): String {
        return userAnswer
    }



}