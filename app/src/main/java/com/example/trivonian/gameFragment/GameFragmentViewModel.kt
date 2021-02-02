package com.example.trivonian.gameFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivonian.dataclasses.GameState
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository
import com.example.trivonian.util.logger.Logable
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GameFragmentViewModel : ViewModel(), Logable {

    private val _repository = QuestionRepository
    private val _gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState.LOADING)
    private val _question: MutableStateFlow<Question> =
        MutableStateFlow(
            Question(
                "",
                "",
                listOf()
            )
        )

    val question: StateFlow<Question> = _question
    val gameState: StateFlow<GameState> = _gameState

    init {
        setup()
    }


    private fun setup() {
        getQuestion()
    }

    fun questionAnswered(answer: String) {
        viewModelScope.launch {
            _repository.saveAnswer(answer)
        }
    }

    fun getNewQuestion() {
        getQuestion()
    }

    private fun getQuestion() {
        viewModelScope.launch {
            _question.value = _repository.getQuestion()
            checkGameState()
            logInformation("populated new Question!")
        }
    }

    private fun checkGameState() {
        viewModelScope.launch {
            val hasAnotherQuestion = async {
                _repository.hasAnotherQuestion()
            }.await()

            if (hasAnotherQuestion) {
                setGameState(GameState.RUNNING)
            } else {
                setGameState(GameState.FINISH)
            }
        }
    }

    private fun setGameState(state: GameState) {
        if (_gameState.value != state) {
            _gameState.value = state
            logInformation("Gamestate was succesfully set to -> ${state}")
        }
    }
}

