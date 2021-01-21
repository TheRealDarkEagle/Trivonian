package com.example.trivonian.questionApi.parser

import com.example.trivonian.dataclasses.Question

/*
Übernimmt das Parsen der Daten
Benötigt dazu noch einen HTMLCharacterCleaner
 */
interface DataParser {

    fun parse(text: String): List<Question>


}
