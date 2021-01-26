package com.example.trivonian.questionApi


import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.parser.DataParser
import com.example.trivonian.questionApi.parser.QuestionParser
import com.example.trivonian.questionApi.requester.DataRequester
import com.example.trivonian.questionApi.requester.QuestionRequester

class QuestionApi : Api {

    private val questionParser: DataParser
        get() = QuestionParser()
    private val questionRequester: DataRequester
        get() = QuestionRequester()

    override suspend fun requestQuestions(): List<Question> {
        return  questionParser.parse(questionRequester.requestQuestions())
    }



}