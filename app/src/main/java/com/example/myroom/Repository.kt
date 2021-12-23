package com.example.myroom

import androidx.lifecycle.LiveData
import com.example.myroom.Daos.StudentDao

class Repository(private val studentDao : StudentDao) {

    var allStudents : LiveData<List<Student>> = studentDao.getNotes()

    suspend fun insertStudent(student : Student){
        studentDao.insertStudent(student)
    }
    suspend fun deleteStudent(student : Student){
        studentDao.deleteStudent(student)
    }

}