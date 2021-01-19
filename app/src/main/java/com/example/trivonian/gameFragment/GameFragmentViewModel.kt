package com.example.trivonian.gameFragment

import androidx.lifecycle.ViewModel
import com.example.trivonian.dataclasses.Question

class GameFragmentViewModel : ViewModel() {

    private var userAnswer: String = ""

    fun getQuestion(): Question {
        //Todo("Frage(n) aus dem Repo erhalten -> Repo anlegen!")
        val question = Question(
            "In the Kingdom Heart series who provides the english voice for Master Eraqus?",
            listOf("Mark Hamill", "Jason Dohring", "Jesse McCartney", "Haley Joel Osment"),
            "Mark Hamill"
        )
        return question
    }

    fun setAnswer(answer: String) {
        userAnswer = answer
    }

}