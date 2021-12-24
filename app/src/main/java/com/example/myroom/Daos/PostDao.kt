package com.example.myroom.Daos

import android.widget.Toast
import com.example.myroom.DataClasses.FirebaseUsers
import com.example.myroom.DataClasses.Post
import com.example.myroom.PostCreatingActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class PostDao {

    private val db = FirebaseFirestore.getInstance()
     val collection = db.collection("posts")
    private val user = Firebase.auth


    @DelicateCoroutinesApi
    fun addPost(text : String){
        val currentUserId = user.currentUser!!.uid
        GlobalScope.launch(Dispatchers.IO){
            val firebaseUserDao = FirebaseUserDao()
            val user = firebaseUserDao.getUserById(currentUserId).await().toObject(FirebaseUsers::class.java)
            val currentTime = System.currentTimeMillis()
            if(user != null){
                val post = Post(text,user,currentTime)
                collection.document().set(post)
            }else{
                Toast.makeText(PostCreatingActivity(),"Please SignIn First To Create An Post",Toast.LENGTH_LONG).show()
            }

        }

    }
}

