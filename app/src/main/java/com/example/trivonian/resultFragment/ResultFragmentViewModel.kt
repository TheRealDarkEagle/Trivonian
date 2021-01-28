package com.example.trivonian.resultFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository
import com.example.trivonian.util.logger.Logable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ResultFragmentViewModel : ViewModel(), Logable {

    private val _repository = QuestionRepository
    private val _questionList = MutableStateFlow<List<Question>?>(null)
    private val _answerList = MutableStateFlow<List<String>?>(null)
    val questionList = _questionList
    val answerList = _answerList

    init {
        viewModelScope.launch {
            _questionList.value = _repository.getAllQuestions()
            answerList.value = _repository.getUserAnswer()
            logInformation("got the needed values!")
        }

    }



}