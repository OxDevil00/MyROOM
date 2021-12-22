package com.example.myroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_item.view.*

class StAdapter (private val listener : OnItemClicked): ListAdapter<Student,StAdapter.StViewHolder>(DifUtilCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StViewHolder {
        return StViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.sNo.setOnClickListener {
            listener.onIdClicked(currentItem)
        }
        holder.bind(currentItem)
    }

    class StViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var sNo: TextView = view.tvID
        private val roll: TextView = view.rollNoID
        private val name: TextView = view.nameID
        private val surname: TextView = view.surnameID

        fun bind(view: Student) {
            sNo.text = view.id.toString()
            roll.text = view.roll_number
            name.text = view.name
            surname.text = view.surname
        }

    }

    class DifUtilCallBack : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }


    }

    interface OnItemClicked{
        fun onIdClicked(student: Student)
    }

}