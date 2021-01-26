package com.example.trivonian.questionApi.datacleaner

import com.example.trivonian.util.logger.Logable

interface DataCleaner: Logable {

    fun clean(text: String): String
}