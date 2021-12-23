package com.example.myroom.Daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myroom.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM student_table")
    fun getNotes() : LiveData<List<Student>>

    @Insert
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)


}