package com.example.trivonian.repository

import android.util.Log
import com.example.trivonian.dataclasses.GameState
import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.QuestionApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object QuestionRepository : Repository {

    private var userAnswers = mutableListOf<String>()
    private var questions = mutableListOf<Question>()
    private var questionIndex = 0

    init {
        logInformation("questionRepository initialized!")
    }

    override suspend fun getQuestion(): Question = withContext(IO) {
        logInformation("The Questionindex -> ${questionIndex}")
        if(questions.isEmpty()) {
            CoroutineScope(IO).launch {
                resetGame()
                questions = QuestionApi().requestQuestions().shuffled().toMutableList()
                logInformation("recieved Questions!")
            }.join()
        }
        questions[questionIndex++]
    }

    override suspend fun saveAnswer(answer: String) {
        withContext(IO) {
            logInformation("saved ${answer} to userAnswers")
            userAnswers.add(answer)
        }
    }

    override suspend fun getUserAnswer(): List<String> = withContext(IO) {
        logInformation("userAnswers -> ${userAnswers}")
        userAnswers.toList()
    }

    override suspend fun resetGame() {
        withContext(IO) {
            questionIndex = 0
            userAnswers = mutableListOf()
            logInformation("Reset Game!")
        }
    }

    override suspend fun hasAnotherQuestion(): Boolean = withContext(IO) {
        val isNewQuestion = questionIndex < questions.size
        logInformation("has new Question -> ${isNewQuestion}")
        isNewQuestion
    }


    override suspend fun getAllQuestions(): List<Question> = withContext(IO) {
        logInformation("Returning questions -> ${questions}")
        questions
    }

}