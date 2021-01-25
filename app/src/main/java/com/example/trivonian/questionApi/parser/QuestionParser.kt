package com.example.trivonian.questionApi.parser

import android.util.Log
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.dataclasses.QuestionsData
import com.example.trivonian.questionApi.datacleaner.DataCleaner
import com.example.trivonian.questionApi.datacleaner.HtmlCharacterCorrector
import com.google.gson.Gson

class QuestionParser: DataParser {

    private val textCleaner: DataCleaner = HtmlCharacterCorrector()

    override fun parse(text: String): List<Question> {
        val cleanedText = textCleaner.clean(text)
        Log.i("QuestionParser", cleanedText)
        return Gson().fromJson(cleanedText, QuestionsData::class.java).questions.map { it }
    }

}