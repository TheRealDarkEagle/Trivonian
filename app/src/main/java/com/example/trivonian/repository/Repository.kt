package com.example.trivonian.repository

import android.util.Log
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.util.logger.Logable

interface Repository : Logable {

    suspend fun getQuestion(): Question
    suspend fun getUserAnswer(): List<String>
    suspend fun getAllQuestions(): List<Question>
    suspend fun hasAnotherQuestion(): Boolean
    suspend fun resetGame()
    suspend fun saveAnswer(answer: String)
}