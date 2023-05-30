package com.example.quiz_app_houcem.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quiz_app_houcem.componets.Questions

@Composable
fun QuizHome(viewModel: QuestionsViewModel = hiltViewModel()  )
{
    Questions(viewModel = viewModel)
}
