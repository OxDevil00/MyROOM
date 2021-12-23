package com.example.myroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myroom.Daos.PostDao
import kotlinx.android.synthetic.main.activity_post_main.*

class PostMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_main)

        btnFloting.setOnClickListener {
            val intent = Intent(this,PostCreatingActivity::class.java)
            startActivity(intent )

        }



    }
}