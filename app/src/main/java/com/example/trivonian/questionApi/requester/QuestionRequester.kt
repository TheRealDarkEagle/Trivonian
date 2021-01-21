package com.example.trivonian.questionApi.requester

import okhttp3.OkHttpClient

class QuestionRequester: DataRequester {

    private lateinit var client: OkHttpClient


    override fun requestQuestions(): String {
        TODO("Not yet implemented")
    }
}