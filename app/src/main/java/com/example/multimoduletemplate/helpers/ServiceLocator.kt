package com.example.multimoduletemplate.helpers

import com.example.multimoduletemplate.layers.ApiHelper
import com.example.multimoduletemplate.layers.ApiService
import com.example.multimoduletemplate.layers.Presenter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"

class ServiceLocator {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private val apiService: ApiService = getRetrofit().create(
        ApiService::class.java)

    private val apiHelper: ApiHelper =
        ApiHelper(apiService)

    val presenter
        get() = Presenter(
            CoroutineDispatcherFactoryDefault(),
            apiHelper
        )
}
