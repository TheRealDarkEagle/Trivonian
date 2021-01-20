package com.example.trivonian.gameFragment

import androidx.lifecycle.ViewModel
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository

class GameFragmentViewModel : ViewModel() {

    private val repository = QuestionRepository()
    private var userAnswer: String = ""
    private var question: Question

    init {
        question = repository.getQuestion()
    }

    fun getAnswers(): List<String> {
       return listOf(question.answers[0],
        question.answers[1],
        question.answers[2],
        question.correctAnswer).shuffled()
    }

    fun setUserAnswer(answer: String) {
        repository.saveAnswer(answer)
    }
    fun getUserAnswer(): String {
        return repository.getUserAnswer()
    }

    fun getQuestionText(): String {
        return question.questionText
    }

    fun getCorrectAnswer(): String {
        return question.correctAnswer
    }




}