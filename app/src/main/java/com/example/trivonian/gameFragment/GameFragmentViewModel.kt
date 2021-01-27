package com.example.trivonian.gameFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository
import com.example.trivonian.util.logger.Logable
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class GameFragmentViewModel : ViewModel(), Logable {


    private val repository = QuestionRepository
    private val _question = MutableStateFlow<Question>(
        Question(
            "",
            "",
            listOf()
        )
    ) //innerhalb des vm wird mit dem privaten objekt gearbeitet!
    val question: StateFlow<Question?> = _question

    var possibleAnswers = listOf<String>()


    init {
        setup()
    }

    private fun setup() {
        viewModelScope.launch {
            _question.value = repository.getQuestion()
            populatePossibleAnswers(
                _question.value.incorrectAnswers,
                _question.value.correctAnswer
            )
            logInformation("populated new Question!")
        }
    }

    private fun populatePossibleAnswers(incorrectAnswers: List<String>, correctAnswer: String) {
        val answerList = mutableListOf<String>()
        answerList.addAll(incorrectAnswers)
        answerList.add(correctAnswer)
        answerList.shuffle()
        possibleAnswers = answerList
    }

    fun questionAnswered(answer: String) {
        viewModelScope.launch {
            repository.saveAnswer(answer)
        }
    }

    /*
    suspend fun hasNewQuestion(): Boolean   {
        viewModelScope.launch {

        }
    }

     */

    fun getNewQuestion() {
        setup()
        /*
        viewModelScope.launch {
            _question.value = repository.getQuestion()
        }

         */
    }

    private suspend fun checkForNewQuestion(): Flow<Boolean> = flow {
        val hasNewQuestion = viewModelScope.async {
            repository.hasAnotherQuestion()
        }
    }
}

