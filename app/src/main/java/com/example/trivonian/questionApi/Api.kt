package com.example.trivonian.questionApi

import com.example.trivonian.dataclasses.Question
import com.example.trivonian.util.logger.Logable

/*
Aufgabe ist die Delegation der einzelnen Schritte zur vollbringung vom Request zum richtigen Objekt
 */
interface Api: Logable {

    suspend fun requestQuestions(): List<Question>

}