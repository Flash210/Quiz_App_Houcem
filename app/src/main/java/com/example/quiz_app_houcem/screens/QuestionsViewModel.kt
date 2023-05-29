package com.example.quiz_app_houcem.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz_app_houcem.data.DataOrExeption
import com.example.quiz_app_houcem.model.QuestionsItem
import com.example.quiz_app_houcem.repository.QuestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuestionsViewModel
@Inject constructor(private val repository: QuestionsRepository):ViewModel(){


    val data:MutableState<DataOrExeption<ArrayList<QuestionsItem>,Boolean,Exception>>
    = mutableStateOf(DataOrExeption(null,true,Exception("")))



    init {
        getAllQuestions()
    }


    private fun getAllQuestions()
    {

        viewModelScope.launch {
            data.value.loading=true
            data.value=repository.getAllQuestions()

            if ( data.value.data.toString().isNotEmpty())
            {
                data.value.loading=false

            }
        }

    }


}