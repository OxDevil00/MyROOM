package com.example.myroom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val roll_number : String,
    val name : String,
    val surname : String

)
