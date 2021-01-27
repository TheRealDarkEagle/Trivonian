package com.example.trivonian.questionApi.parser

import android.util.Log
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.dataclasses.QuestionsData
import com.example.trivonian.questionApi.datacleaner.DataCleaner
import com.example.trivonian.questionApi.datacleaner.HtmlCharacterCorrector
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class QuestionParser: DataParser {

    private val textCleaner: DataCleaner = HtmlCharacterCorrector()

    override suspend fun parse(text: String): List<Question> = withContext(IO) {
            val cleanedText = textCleaner.clean(text)
            Log.i("QuestionParser", cleanedText)
            val question = Gson().fromJson(cleanedText, QuestionsData::class.java).questions.map { it }.first()
            return@withContext listOf(question)
    }

}