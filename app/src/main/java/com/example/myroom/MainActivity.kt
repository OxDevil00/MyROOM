package com.example.myroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), StAdapter.OnItemClicked {

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = StudentDatabase.getDatabse(applicationContext).studentDao()
        val repository = Repository(dao)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]

        recView.layoutManager = LinearLayoutManager(this)
        val adapter = StAdapter(this)
        recView.adapter = adapter

        mainViewModel.allStudent.observe(this,{
            adapter.submitList(it)
        })

        btnSignOut.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this,SignInActivity::class.java)
            Toast.makeText(this,"Sign Out Success", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

        btnAdd.setOnClickListener {
            GlobalScope.launch {
                mainViewModel.insertStudent(Student(0,etRollNo.text.toString(),etName.text.toString(),etSurname.text.toString()))
                etRollNo.text = null
                etName.text = null
                etSurname.text = null
            }
        }

    }
    override fun onIdClicked(student: Student) {
        GlobalScope.launch {
            mainViewModel.deleteStudent(student)
        }
    }

}