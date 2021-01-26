package com.example.trivonian.questionApi.requester

import com.example.trivonian.util.logger.Logable

/*
Übernimmt das Requesten der Daten
 */

interface DataRequester: Logable {

    fun requestQuestions(): String

}