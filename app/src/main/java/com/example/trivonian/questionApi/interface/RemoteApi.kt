package com.example.trivonian.questionApi.`interface`

import com.example.trivonian.dataclasses.Question

interface RemoteApi {

    val questionParser: QuestionParser

    fun requestQuestions(): List<Question>

}