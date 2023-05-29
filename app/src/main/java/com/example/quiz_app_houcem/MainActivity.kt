package com.example.quiz_app_houcem

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quiz_app_houcem.screens.QuestionsViewModel
import com.example.quiz_app_houcem.ui.theme.Quiz_App_HoucemTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Quiz_App_HoucemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   // Greeting("Android")
                    QuizHome()
                }
            }
        }
    }
}



@Composable
fun QuizHome(viewModel: QuestionsViewModel =hiltViewModel()  )
{
Questions(viewModel = viewModel)
}


 @Composable
 fun Questions(viewModel: QuestionsViewModel)
 {
     val questions=viewModel.data.value.data?.toMutableList()

     Log.d("xx","Questions ${questions?.size}")

     if ( viewModel.data.value.loading ==true )
     {
         Log.d("xx","Questions ..loading ")

     }else
     {
         questions?.forEach { questionsItem ->

             Log.d("xx","Questions :${questionsItem.question}")


         }
     }


 }





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Quiz_App_HoucemTheme {
       // Greeting("Android")
    }
}