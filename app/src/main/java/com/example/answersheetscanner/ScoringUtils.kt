package com.example.answersheetscanner

object ScoringUtils {
    fun compareAnswers(user: AnswerSheet, key: AnswerSheet): Float {
        val total = key.answers.size
        val correct = user.answers.zip(key.answers).count { it.first == it.second }
        return (correct.toFloat() / total) * 100
    }
}
