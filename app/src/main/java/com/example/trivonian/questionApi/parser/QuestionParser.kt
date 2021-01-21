package com.example.trivonian.questionApi.parser

import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.datacleaner.DataCleaner
import com.example.trivonian.questionApi.datacleaner.HtmlCharacterCorrector
import com.example.trivonian.questionApi.parser.DataParser
import java.io.Reader

class QuestionParser: DataParser {

    private val textCleaner: DataCleaner = HtmlCharacterCorrector()

    override fun parse(charStream: Reader): List<Question> {
        TODO("Not yet implemented")
    }

}