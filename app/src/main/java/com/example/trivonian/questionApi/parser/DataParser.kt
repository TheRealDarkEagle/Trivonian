package com.example.trivonian.questionApi.parser

import com.example.trivonian.dataclasses.Question
import com.example.trivonian.util.logger.Logable

/*
Übernimmt das Parsen der Daten
Benötigt dazu noch einen HTMLCharacterCleaner
 */
interface DataParser: Logable {

    fun parse(text: String): List<Question>


}
