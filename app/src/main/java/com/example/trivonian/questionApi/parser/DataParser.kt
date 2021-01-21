package com.example.trivonian.questionApi.parser

import com.example.trivonian.dataclasses.Question
import java.io.Reader

/*
Übernimmt das Parsen der Daten
Benötigt dazu noch einen HTMLCharacterCleaner
 */
interface DataParser {

    fun parse(charStream: Reader): List<Question>


}
