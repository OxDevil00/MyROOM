package com.example.myroom.Daos

import com.example.myroom.DataClasses.FirebaseUsers
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirebaseUserDao {

    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("users")

    fun addUser(user : FirebaseUsers?){
        user?.let {
            GlobalScope.launch(Dispatchers.IO){
                userCollection.document(user.uId).set(it)
            }
        }
    }



}