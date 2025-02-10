package com.example.loginfirebaseapp.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User Table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val name: String,
    val email: String
)
