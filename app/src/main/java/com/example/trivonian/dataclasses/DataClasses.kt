package com.example.trivonian.dataclasses

import com.google.gson.annotations.SerializedName

data class QuestionsData (
    val responseCode: Long,
    @SerializedName("results")
    val questions: List<Question>
)
data class Question (
    //val category: String,
    //val type: Type,
    //val difficulty: Difficulty,
    @SerializedName("question")
    val questionText: String,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>
)

enum class Difficulty {
    Easy,
    Hard,
    Medium
}

enum class Type {
    Multiple
}
