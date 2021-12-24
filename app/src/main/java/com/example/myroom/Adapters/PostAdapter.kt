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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PostAdapter(options: FirestoreRecyclerOptions<Post>,private val listener : IPostAdapter) : FirestoreRecyclerAdapter<Post,PostAdapter.PostViewHolder>(
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

        holder.likeImage.setOnClickListener {
            listener.onLiked(snapshots.getSnapshot(holder.adapterPosition).id)
        }

        val auth = Firebase.auth
        val currentUserId = auth.currentUser!!.uid

        val isLiked = model.liked.contains(currentUserId)
        if (isLiked){
            holder.likeImage.setImageResource(R.drawable.ic_baseline_like_24)
        }else{
            holder.likeImage.setImageResource(R.drawable.ic_unlike)
        }

    }

    class PostViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val postText : TextView = view.findViewById(R.id.tvPost)
        val userText : TextView = view.findViewById(R.id.tvUserName)
        val createTimeText : TextView = view.findViewById(R.id.tvPostTime)
        val likeText : TextView = view.findViewById(R.id.tvLikeCount)
        val userImage : ImageView = view.findViewById(R.id.imgUser)
        val likeImage : ImageView = view.findViewById(R.id.imgLike)

    }

    interface IPostAdapter {
        fun onLiked(uId : String)
    }

}