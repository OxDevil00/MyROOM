package com.example.myroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myroom.Adapters.PostAdapter
import com.example.myroom.Daos.PostDao
import com.example.myroom.DataClasses.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_post_main.*
import kotlinx.android.synthetic.main.firebase_recycler_item.*

class PostMainActivity : AppCompatActivity(), PostAdapter.IPostAdapter {
    lateinit var postAdapter: PostAdapter
    lateinit var postDao: PostDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_main)

        btnFloting.setOnClickListener {
            val intent = Intent(this,PostCreatingActivity::class.java)
            startActivity(intent )
        }
        setUpPostRecyclerView()

    }
    private fun setUpPostRecyclerView() {
        postDao = PostDao()
        val postCollection = postDao.collection
        val query = postCollection.orderBy("createTime",Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post::class.java).build()

        postAdapter = PostAdapter(recyclerViewOptions,this)

        recyclerPostMain.adapter = postAdapter
        recyclerPostMain.layoutManager = LinearLayoutManager(this)

    }

    override fun onStart() {
        super.onStart()
        postAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        postAdapter.stopListening()
    }

    override fun onLiked(uId: String) {
        postDao.updateLikes(uId)
    }

}