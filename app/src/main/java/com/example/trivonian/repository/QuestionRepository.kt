package com.example.trivonian.repository

import android.util.Log
import com.example.trivonian.dataclasses.Question
import dagger.BindsOptionalOf
import kotlin.math.log

private fun getQuestions(): List<Question> {
    return listOf(Question(
        "In the Kingdom Heart series who provides the english voice for Master Eraqus?",
        listOf("Jason Dohring", "Jesse McCartney", "Haley Joel Osment"),
        "Mark Hamill"
    ),
        Question("Which one of these is not a real game in the Dungeons &amp; Dragons series?",
            listOf("Advanced Dungeons &amp; Dragons","Dungeons &amp; Dragons 3.5th edition","Advanced Dungeons &amp; Dragons 2nd edition"),
            "Extreme Dungeons &amp; Dragons")
    )
}

object QuestionRepository {

    private var userAnswers = mutableListOf<String>()
    private val questions = getQuestions()
    private var questionIndex = 0

    init {
        log("QuestionRepository initialized!")
    }

    fun getQuestion(): Question {
        log("The Questionindex -> ${questionIndex}")
        return questions[questionIndex++]
    }

    fun saveAnswer(answer: String) {
        userAnswers.add(answer)
    }

    fun getUserAnswer(): List<String> {
        return userAnswers
    }

    fun resetGame() {
        questionIndex = 0
        userAnswers = mutableListOf()
        log("Reset Game!")
    }

    fun isAnotherQuestion(): Boolean {
        val isNewQuestion = questionIndex < questions.size
        log("has new Question -> ${isNewQuestion}")
        return isNewQuestion
    }

    private fun log(msg: String) {
        val logTag = "QuestionRepository"
        Log.i(logTag,msg)
    }
}