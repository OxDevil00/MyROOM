package com.example.myroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(private val repository: Repository) : ViewModel() {

    val allStudent : LiveData<List<Student>> = repository.allStudents



    suspend fun insertStudent(student: Student){
        repository.insertStudent(student)
    }

    suspend fun deleteStudent(student: Student){
        repository.deleteStudent(student)
    }

}

class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }


}