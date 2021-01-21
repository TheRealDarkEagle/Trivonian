package com.example.trivonian.repository

import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.QuestionApi
import com.example.trivonian.questionApi.Api

class QuestionRepository {

    private var userAnswer: String = ""
    private val questionApi: Api = QuestionApi()

    fun getQuestion(): Question {
        return Question(
            "In the Kingdom Heart series who provides the english voice for Master Eraqus?",
            listOf("Jason Dohring", "Jesse McCartney", "Haley Joel Osment"),
            "Mark Hamill"
        )
    }

    fun saveAnswer(answer: String) {
        userAnswer = answer
    }

    fun getUserAnswer(): String {
        return userAnswer
    }
}