package com.example.multimoduletemplate.layers

import com.example.multimoduletemplate.model.Result
import com.example.multimoduletemplate.model.User
import retrofit2.http.GET


interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>?

}

class ApiHelper(val api: ApiService) {

    suspend fun getUsers(): Result {
        val users = api.getUsers()

        return if (users != null && users.isNotEmpty()) {
            Result.Success(users)
        } else
            Result.Failure
    }

}