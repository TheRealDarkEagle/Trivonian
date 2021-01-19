package com.example.trivonian.repository

import com.example.trivonian.dataclasses.Question

class QuestionRepository {

    private var userAnswer: String = ""

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
}