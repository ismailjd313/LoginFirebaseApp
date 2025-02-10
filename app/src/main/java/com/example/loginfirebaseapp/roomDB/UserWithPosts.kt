package com.example.loginfirebaseapp.roomDB

import androidx.room.Embedded
import androidx.room.Relation


data class UserWithPosts(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "authorId"
    )
    val post: List<Post>
)
