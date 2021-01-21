package com.example.trivonian.questionApi.datacleaner

interface DataCleaner {

    fun clean(text: String): String
}