package com.example.loginfirebaseapp.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface AppDao {
    //Insert Operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: Comment)


    //Queries
    @Transaction
    @Query("SELECT * FROM `User Table` WHERE userId = :userId")
    suspend fun getUserWithPosts(userId: Int): List<UserWithPosts>

    @Transaction
    @Query("SELECT * FROM `post table` WHERE postId = :postId")
    suspend fun getPostWithComments(postId: Int): List<PostWithComments>

}