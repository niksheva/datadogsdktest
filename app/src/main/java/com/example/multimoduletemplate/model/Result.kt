package com.example.multimoduletemplate.model

sealed class Result {
    data class Success(val users: List<User>) : Result()
    object Failure : Result()
}