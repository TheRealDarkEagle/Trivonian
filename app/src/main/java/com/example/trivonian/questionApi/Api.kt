package com.example.trivonian.questionApi

import com.example.trivonian.dataclasses.Question
import com.example.trivonian.util.logger.Logable

interface Api: Logable {

    suspend fun requestQuestions(): List<Question>

}