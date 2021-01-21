package com.example.trivonian.questionApi

import com.example.trivonian.dataclasses.Question

/*
Aufgabe ist die Delegation der einzelnen Schritte zur vollbringung vom Request zum richtigen Objekt
 */
interface Api {

    fun requestQuestions(): List<Question>

}