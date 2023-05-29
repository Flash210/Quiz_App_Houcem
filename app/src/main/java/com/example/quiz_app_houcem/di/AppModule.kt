package com.example.quiz_app_houcem.di

import com.example.quiz_app_houcem.details.Constants
import com.example.quiz_app_houcem.network.QuestionApi
import com.example.quiz_app_houcem.repository.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    /*  in this object we add all the providers for this app
    * like reposiotry or dao
    *
    *
    *
    * */


    /*
    to any class who need this dependency we create this provider .
    its like a databse  in room any one who need some info can acces to it
    and its singelton only one instance
     */

    @Singleton
    @Provides
    fun provideQuestionsApi():QuestionApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionApi::class.java)
    }


    @Singleton
    @Provides
    fun  provideQuestionsRepository(api: QuestionApi)=QuestionsRepository(api)
}