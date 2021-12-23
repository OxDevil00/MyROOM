package com.example.myroom.Daos

import com.example.myroom.DataClasses.FirebaseUsers
import com.example.myroom.DataClasses.Post
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class PostDao {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("posts")
    private val user = Firebase.auth


    @DelicateCoroutinesApi
    fun addPost(text : String){
        val currentUserId = user.currentUser!!.uid
        GlobalScope.launch(Dispatchers.IO){
            val firebaseUserDao = FirebaseUserDao()
            val user = firebaseUserDao.getUserById(currentUserId).await().toObject(FirebaseUsers::class.java)!!
            val currentTime = System.currentTimeMillis()
            val post = Post(text,user,currentTime)
            collection.document().set(post)
        }

    }
}

