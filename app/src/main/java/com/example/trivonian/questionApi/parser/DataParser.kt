package com.example.trivonian.questionApi.parser

import com.example.trivonian.dataclasses.Question
import com.example.trivonian.util.logger.Logable

interface DataParser: Logable {

    suspend fun parse(text: String): List<Question>


}
