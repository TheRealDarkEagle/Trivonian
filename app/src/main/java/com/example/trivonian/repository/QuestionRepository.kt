package com.example.trivonian.repository

import com.example.trivonian.dataclasses.Question
import com.example.trivonian.questionApi.QuestionApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
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
        logInformation("The QuestionIndex -> $questionIndex")
        questions[questionIndex++]
    }

    override suspend fun loadNewQuestions() {
        questions = CoroutineScope(IO).async {
            resetGame()
            QuestionApi().requestQuestions().shuffled().toMutableList()
        }.await()

    }

    override suspend fun saveAnswer(answer: String) {
        withContext(IO) {
            logInformation("saved $answer to userAnswers")
            userAnswers.add(answer)
        }
    }

    override fun getUserAnswer(): List<String> {
        logInformation("userAnswers -> $userAnswers")
        return userAnswers.toList()
    }

    override fun resetGame() {
        questionIndex = 0
        userAnswers = mutableListOf()
        questions = mutableListOf()
        logInformation("Reset Game!")
    }

    override suspend fun hasAnotherQuestion(): Boolean = withContext(IO) {
        val isNewQuestion = questionIndex < questions.size
        logInformation("has new Question -> $isNewQuestion")
        isNewQuestion
    }


    override fun getAllQuestions(): List<Question> {
        logInformation("Returning questions -> $questions")
        return questions
    }

}