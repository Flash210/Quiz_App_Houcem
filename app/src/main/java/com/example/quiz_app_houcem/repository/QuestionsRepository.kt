package com.example.quiz_app_houcem.repository

import android.util.Log
import com.example.quiz_app_houcem.data.DataOrExeption
import com.example.quiz_app_houcem.model.Questions
import com.example.quiz_app_houcem.model.QuestionsItem
import com.example.quiz_app_houcem.network.QuestionApi
import javax.inject.Inject

class QuestionsRepository @Inject constructor(

    private val api:QuestionApi) {

    private val dataOrException: DataOrExeption<ArrayList<QuestionsItem>, Boolean, Exception> =
        DataOrExeption<ArrayList<QuestionsItem>,
                Boolean,
                Exception>()

    suspend fun getAllQuestions(): DataOrExeption<ArrayList<QuestionsItem>, Boolean, Exception> {


        try {
            dataOrException.loading = true
            dataOrException.data = api.getALlQUestions()

            if (dataOrException.data.toString().isNotEmpty())
                dataOrException.loading = false

        } catch (e: Exception) {
            dataOrException.e = e
            Log.d("Exc", "GetAllQuestions .. ${dataOrException.e!!.localizedMessage}")
        }

        return dataOrException
    }
}


