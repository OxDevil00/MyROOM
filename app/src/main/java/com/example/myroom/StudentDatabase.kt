package com.example.myroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myroom.Daos.StudentDao

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao() : StudentDao

    companion object{

        @Volatile
        private var INSTANCE : StudentDatabase ? = null

        fun getDatabse(context: Context) : StudentDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "StudentDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}