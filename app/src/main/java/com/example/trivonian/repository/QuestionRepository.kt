package com.example.trivonian.repository

import com.example.trivonian.dataclasses.Question

object QuestionRepository {

    private var userAnswers = mutableListOf<String>()
    private val questions = listOf(Question(
        "In the Kingdom Heart series who provides the english voice for Master Eraqus?",
        listOf("Jason Dohring", "Jesse McCartney", "Haley Joel Osment"),
        "Mark Hamill"
    ),
    Question("Which one of these is not a real game in the Dungeons &amp; Dragons series?",
        listOf("Advanced Dungeons &amp; Dragons","Dungeons &amp; Dragons 3.5th edition","Advanced Dungeons &amp; Dragons 2nd edition"),
        "Extreme Dungeons &amp; Dragons")
    )

    fun getQuestion(): Question {
        return questions[userAnswers.size]
    }

    fun saveAnswer(answer: String) {
        userAnswers.add(answer)
    }

    fun getUserAnswer(): List<String> {
        return userAnswers
    }
}