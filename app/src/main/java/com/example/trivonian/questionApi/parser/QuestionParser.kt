package com.example.trivonian.questionApi.parser

import com.example.trivonian.dataclasses.Question
import com.example.trivonian.dataclasses.QuestionsData
import com.example.trivonian.questionApi.datacleaner.DataCleaner
import com.example.trivonian.questionApi.datacleaner.HtmlCharacterCorrector
import com.google.gson.Gson

class QuestionParser: DataParser {

    private val textCleaner: DataCleaner = HtmlCharacterCorrector()

    override fun parse(text: String): List<Question> {
        return Gson().fromJson(text, QuestionsData::class.java).questions.map { it }
    }

    /*
    Question(
            "In the Kingdom Heart series who provides the english voice for Master Eraqus?",
            listOf("Jason Dohring", "Jesse McCartney", "Haley Joel Osment"),
            "Mark Hamill"
        )
     */

}