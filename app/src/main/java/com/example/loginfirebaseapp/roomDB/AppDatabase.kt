package com.example.loginfirebaseapp.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        User::class, Post::class, Comment::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}