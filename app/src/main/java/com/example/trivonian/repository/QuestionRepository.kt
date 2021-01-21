package com.example.trivonian.repository

import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.QuestionApi
import com.example.trivonian.questionApi.Api

class QuestionRepository {

    private var userAnswer: String = ""
    private val questionApi: Api = QuestionApi()

    fun getQuestion(): Question {
        return questionApi.requestQuestions().shuffled().first()
    }

    fun saveAnswer(answer: String) {
        userAnswer = answer
    }

    fun getUserAnswer(): String {
        return userAnswer
    }
}