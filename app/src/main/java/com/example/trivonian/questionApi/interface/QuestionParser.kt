package com.example.trivonian.questionApi.`interface`

import com.example.trivonian.dataclasses.Question
import java.io.Reader

interface QuestionParser {

    fun parse(charStream: Reader): List<Question>

}
