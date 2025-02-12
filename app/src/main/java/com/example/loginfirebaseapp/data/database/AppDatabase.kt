package com.example.loginfirebaseapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loginfirebaseapp.data.dao.CategoryDao
import com.example.loginfirebaseapp.data.dao.SubtaskDao
import com.example.loginfirebaseapp.data.dao.TaskDao
import com.example.loginfirebaseapp.model.Category
import com.example.loginfirebaseapp.model.Subtask
import com.example.loginfirebaseapp.model.Task

@Database(
    entities = [Category::class, Task::class, Subtask::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun taskDao(): TaskDao
    abstract fun subtaskDao(): SubtaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_database"
                )
                    .fallbackToDestructiveMigration() // Handles DB version upgrades by clearing data
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}