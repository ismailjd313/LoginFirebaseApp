package com.example.loginfirebaseapp.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.loginfirebaseapp.model.Category
import com.example.loginfirebaseapp.model.Task

data class CategoryWithTasks(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryOwnerId"
    )
    val tasks: List<Task>
)
