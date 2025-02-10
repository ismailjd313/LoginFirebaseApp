package com.example.loginfirebaseapp.roomDB

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Post Table",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["authorId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Post(
    @PrimaryKey(autoGenerate = true)
    val postId: Int = 0,
    val title: String,
    val content: String,
    val authorId: Int
)
