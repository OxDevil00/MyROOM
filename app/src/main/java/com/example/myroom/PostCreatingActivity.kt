package com.example.myroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myroom.Daos.PostDao
import kotlinx.android.synthetic.main.activity_post_creating.*

class PostCreatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_creating)

        btnAddPost.setOnClickListener {
            val text = etPost.text.toString()
            if (text.isNotEmpty()){
                val postDao = PostDao()
                postDao.addPost(text)
                etPost.text = null
                finish()
            }
        }

    }
}