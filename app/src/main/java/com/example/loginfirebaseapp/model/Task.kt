package com.example.loginfirebaseapp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryOwnerId"],
            onDelete = ForeignKey.CASCADE // Delete tasks when category is deleted
        )
    ]
)
data class Task(
    @PrimaryKey(autoGenerate = true) val taskId: Int = 0,
    val title: String,
    val description: String?,
    val dueDate: Long?, // Timestamp (nullable)
    val isCompleted: Boolean = false,
    val categoryOwnerId: Int // Foreign key referencing Category
)
