package com.example.loginfirebaseapp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "subtasks",
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["taskId"],
            childColumns = ["taskOwnerId"],
            onDelete = ForeignKey.CASCADE // Delete subtasks when task is deleted
        )
    ]
)
data class Subtask(
    @PrimaryKey(autoGenerate = true) val subtaskId: Int = 0,
    val title: String,
    val isCompleted: Boolean = false,
    val taskOwnerId: Int // Foreign key referencing Task
)
