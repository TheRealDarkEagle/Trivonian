package com.example.trivonian.repository

import android.util.Log
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.util.logger.Logable

interface Repository : Logable {

    suspend fun getQuestion(): Question
    suspend fun hasAnotherQuestion(): Boolean
    suspend fun saveAnswer(answer: String)
    fun resetGame()
    fun getUserAnswer(): List<String>
    fun getAllQuestions(): List<Question>
}