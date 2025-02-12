package com.example.loginfirebaseapp.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.loginfirebaseapp.model.Subtask
import com.example.loginfirebaseapp.model.Task

data class TaskWithSubtasks(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "taskOwnerId"
    )
    val subtasks: List<Subtask>
)
