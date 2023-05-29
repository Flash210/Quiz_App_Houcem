package com.example.quiz_app_houcem.network

import com.example.quiz_app_houcem.model.Questions
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface QuestionApi {

    @GET("world.json")
    suspend fun getALlQUestions():Questions


}