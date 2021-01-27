package com.example.trivonian.resultFragment

import androidx.lifecycle.ViewModel
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.repository.QuestionRepository

class ResultFragmentViewModel : ViewModel() {

    private val repository = QuestionRepository
    val questionList: List<Question>
    val answerList: List<String>

    init {

        questionList = listOf<Question>()//repository.getAllQuestions()
        answerList = listOf<String>() //repository.getUserAnswer()
    }



}