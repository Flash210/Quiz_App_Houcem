package com.example.quiz_app_houcem.data

data class DataOrExeption<T,Boolean,E:Exception>(

var data:T?=null,
var loading:kotlin.Boolean?=null,
var e:E?=null



)
