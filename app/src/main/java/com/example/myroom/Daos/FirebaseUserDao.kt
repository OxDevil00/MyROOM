package com.example.myroom.Daos

import com.example.myroom.DataClasses.FirebaseUsers
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
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

    fun getUserById(uId : String) : Task<DocumentSnapshot>{
        return userCollection.document(uId).get()
    }

}