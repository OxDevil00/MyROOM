package com.example.myroom.DataClasses

data class Post(
    val text : String = "",
    val createdBy : FirebaseUsers = FirebaseUsers(),
    val createTime : Long = 0L,
    val liked : ArrayList<String> = ArrayList()
)
