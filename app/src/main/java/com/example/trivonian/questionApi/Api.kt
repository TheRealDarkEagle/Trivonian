package com.example.trivonian.questionApi

import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.`interface`.QuestionParser
import com.example.trivonian.questionApi.`interface`.RemoteApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody

class Api : RemoteApi{

    /*
        gson.fromJason erhält einen reader und die jeweilge klasse in was es konventiert werden soll
        den reader erhlate ich durch response.body.charstream -> das vllt einfach in ein objekt wrappen und dadurch all das kapseln?


     */

    override val questionParser: QuestionParser
        get() = TODO("Not yet implemented")

    override fun requestQuestions(): List<Question> {
        TODO("Not yet implemented")
    }

    private fun test() {

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://publicobject.com/helloworld.txt")
            .build()

        val response = client.newCall(request).execute();
        var responseBody: ResponseBody

        if (response.isSuccessful && response.body != null) {

        }
        val reader = response.body?.charStream()
        //val test = //Gson().fromJson(response.body()?.charStream())
    }

}