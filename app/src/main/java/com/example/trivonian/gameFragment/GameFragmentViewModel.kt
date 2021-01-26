package com.example.trivonian.gameFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameFragmentViewModel : ViewModel() {


    private val repository = QuestionRepository
    private val _question = MutableStateFlow<Question?>(null) //innerhalb des vm wird mit dem privaten objekt gearbeitet!
    val question: StateFlow<Question?> = _question

    var questionText: String = ""
    lateinit var possibleAnswers: List<String>


    init {
        repository.resetGame()
        setup()
    }

    private fun setup() {
        viewModelScope.launch {
            question.value = repository.getQuestion()
              //  question =
           // questionText = question.value.questionText
            populatePossibleAnswers()
        }
        _

    }

    private fun populatePossibleAnswers() {
        val answerList = mutableListOf<String>()
        answerList.add(question.correctAnswer)
        question.incorrectAnswer.map {
            answerList.add(it)
        }

        answerList.shuffle()
        possibleAnswers  = answerList

    }

    fun questionAnswered(answer: String) {
        repository.saveAnswer(answer)
    }

    fun hasNewQuestion(): Boolean {
        return checkForNewQuestion()
    }

    fun getNewQuestion() {
        setup()
    }

    private fun checkForNewQuestion(): Boolean {
        return repository.isAnotherQuestion()
    }

}