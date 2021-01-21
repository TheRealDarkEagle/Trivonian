package com.example.trivonian.questionApi


import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.parser.DataParser
import com.example.trivonian.questionApi.requester.DataRequester
import com.example.trivonian.questionApi.parser.QuestionParser
import com.example.trivonian.questionApi.requester.QuestionRequester
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody

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
        questionRequester.requestQuestions()
        TODO("Not yet implemented")
    }

    private fun test() {

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://publicobject.com/helloworld.txt")
            .build()

        val response = client.newCall(request).execute()
        var responseBody: ResponseBody

        if (response.isSuccessful && response.body != null) {

        }
        val reader = response.body?.charStream()
        //val test = //Gson().fromJson(response.body()?.charStream())
    }

}