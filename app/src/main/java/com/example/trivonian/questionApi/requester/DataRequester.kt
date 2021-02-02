package com.example.trivonian.questionApi.requester

import com.example.trivonian.util.logger.Logable

interface DataRequester: Logable {

    suspend fun requestQuestions(): String

}