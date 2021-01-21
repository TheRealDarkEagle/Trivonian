package com.example.trivonian.questionApi


import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.parser.DataParser
import com.example.trivonian.questionApi.requester.DataRequester
import com.example.trivonian.questionApi.parser.QuestionParser
import com.example.trivonian.questionApi.requester.QuestionRequester

class QuestionApi : Api {

    /*
        gson.fromJason erhält einen reader und die jeweilge klasse in was es
        konventiert werden soll
        den reader erhlate ich durch response.body.charstream -> das vllt
        einfach in ein objekt wrappen und dadurch all das kapseln?
     */

    private val questionParser: DataParser
        get() = QuestionParser()
    private val questionRequester: DataRequester
        get() = QuestionRequester()

    override fun requestQuestions(): List<Question> {
        return  questionParser.parse(questionRequester.requestQuestions())
        //return listOf(Question("a", "a", listOf("a")))
    }



}