package com.example.myroom.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myroom.DataClasses.Post
import com.example.myroom.R
import com.example.myroom.Utils.TimeUtil
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class PostAdapter(options: FirestoreRecyclerOptions<Post>) : FirestoreRecyclerAdapter<Post,PostAdapter.PostViewHolder>(
    options
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.firebase_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {
        holder.postText.text = model.text
        holder.userText.text = model.createdBy.displayName
        holder.createTimeText.text = TimeUtil.getTimeAgo(model.createTime)
        holder.likeText.text = model.liked.size.toString()
        Glide.with(holder.userImage.context).load(model.createdBy.imageUrl).circleCrop().into(holder.userImage)

    }

    class PostViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val postText : TextView = view.findViewById(R.id.tvPost)
        val userText : TextView = view.findViewById(R.id.tvUserName)
        val createTimeText : TextView = view.findViewById(R.id.tvPostTime)
        val likeText : TextView = view.findViewById(R.id.tvLikeCount)
        val userImage : ImageView = view.findViewById(R.id.imgUser)
        val likeImage : ImageView = view.findViewById(R.id.imgLike)
    }


}