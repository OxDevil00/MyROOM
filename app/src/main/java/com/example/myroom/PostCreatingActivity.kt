package com.example.myroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myroom.Daos.PostDao
import kotlinx.android.synthetic.main.activity_post_creating.*

class PostCreatingActivity : AppCompatActivity() {
    private lateinit var postDao : PostDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_creating)
        postDao = PostDao()
        btnAddPost.setOnClickListener {
            val text = etPost.text.toString()
            if (text.isNotEmpty()){
                postDao.addPost(text)
                etPost.text = null
                finish()
            }
        }

    }
}