package com.example.trivonian.repository

import android.util.Log
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.QuestionApi

object QuestionRepository {

    private var userAnswers = mutableListOf<String>()
    private val questions: List<Question>
    private var questionIndex = 0

    init {
        log("QuestionRepository initialized!")
        questions = QuestionApi().requestQuestions().shuffled()
    }

    fun getQuestion(): Question {
        log("The Questionindex -> ${questionIndex}")
        return questions[questionIndex++]
    }

    fun saveAnswer(answer: String) {
        log("saved ${answer} to userAnswers")
        userAnswers.add(answer)
    }

    fun getUserAnswer(): List<String> {
        log("userAnswers -> ${userAnswers}")
        return userAnswers.toList()
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

    fun getAllQuestions(): List<Question> {
        log("Returning questions -> ${questions}")
        return questions
    }

}