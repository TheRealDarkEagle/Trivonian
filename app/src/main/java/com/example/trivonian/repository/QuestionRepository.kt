package com.example.trivonian.repository

import android.util.Log
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.QuestionApi
import com.example.trivonian.questionApi.Api

class QuestionRepository {

    private var userAnswer: String = ""
    private val questionApi: Api = QuestionApi()

    fun getQuestion(): Question {
        val questions = questionApi.requestQuestions()
        questions.map {
            Log.i("QuestionRepository", it.toString())
        }
        return questions
            .shuffled()
            .first()
    }

    fun saveAnswer(answer: String) {
        userAnswer = answer
    }

    fun getUserAnswer(): String {
        return userAnswer
    }
}