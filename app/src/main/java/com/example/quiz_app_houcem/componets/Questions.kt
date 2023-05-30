package com.example.quiz_app_houcem.componets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz_app_houcem.details.AppColor
import com.example.quiz_app_houcem.model.QuestionsItem
import com.example.quiz_app_houcem.screens.QuestionsViewModel


@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()

    Log.d("xx", "Questions ${questions?.size}")

    if (viewModel.data.value.loading == true) {
        Log.d("xx", "Questions ..loading ")

    } else {
        if ( questions!=null)
        {
            QuestionsDisplay(questionsItem = questions.first(),
           )
        }

    }


}


//@Preview
@Composable
fun QuestionsDisplay(
    questionsItem: QuestionsItem,
  //  questionIndex: MutableState<Int>,
   // viewModel: QuestionsViewModel,
   // onNextClicked: (Int) -> Unit
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    val choicesState = remember(questionsItem) {
        questionsItem.choices.toMutableList()
    }

    val answerState = remember(questionsItem) {
        mutableStateOf<Int?>(null)
    }

    val correctAnswerState = remember(questionsItem) {
        mutableStateOf<Boolean?>(null)
    }
    val updateAnswer: (Int) -> Unit = remember(questionsItem) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState[it] == questionsItem.answer
        }
    }



    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(4.dp),
        color = AppColor.mDarkPurple
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start


        ) {
            QuestionTrucker()
            DrawDottedLine(pathEffect = pathEffect)

            Column(
                modifier = Modifier
                    .padding(6.dp)
                    .align(alignment = Alignment.Start)
                    .fillMaxHeight(0.3f)
            ) {
                Text(
                    text = questionsItem.question,
                    fontSize = 17.sp,
                    color = AppColor.mOffWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp
                )


                choicesState.forEachIndexed { index, answerText ->
                    Row(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                width = 4.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        AppColor.mDarkPurple,
                                        AppColor.mDarkPurple
                                    )
                                ),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .clip(
                                RoundedCornerShape(
                                    topEndPercent = 50,
                                    topStartPercent = 50,
                                    bottomEndPercent = 50,
                                    bottomStartPercent = 50
                                )
                            )
                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        RadioButton(
                            selected = (answerState.value == index),
                            onClick = {
                                updateAnswer(index)
                            },
                            modifier = Modifier.padding(start = 16.dp),

                            colors = RadioButtonDefaults.colors(
                                selectedColor = if (correctAnswerState.value == true && index == answerState.value) {
                                    Color.Green.copy(alpha = 0.2f)
                                } else {
                                    Color.Red.copy(alpha = 0.2f)
                                }
                            )
                        )
                        //end Radio Buttton


                    }

                }
            }
        }


    }


}


@Composable

fun QuestionTrucker(counter: Int = 10, outOf: Int = 100) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(
                    textIndent = TextIndent.None
                )
            ) {
                withStyle(
                    style = SpanStyle(
                        color = AppColor.mLightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp
                    )
                ) {
                    append("Questions $counter/")

                    withStyle(
                        style = SpanStyle(

                            color = AppColor.mLightGray,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        )
                    ) {
                        append("$outOf")
                    }
                }
            }

        },

        modifier = Modifier.padding(20.dp)
    )
}


@Composable
fun DrawDottedLine(pathEffect: PathEffect) {

    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp),
        onDraw = {
            drawLine(
                color = AppColor.mLightGray,
                start = Offset(0f, 0f),
                end = Offset(size.width, y = 0f),
                pathEffect = pathEffect
            )

        }


    )


}