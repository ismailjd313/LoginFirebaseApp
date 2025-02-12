package com.example.loginfirebaseapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.loginfirebaseapp.model.Task
import com.example.loginfirebaseapp.model.relation.TaskWithSubtasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks WHERE categoryOwnerId = :categoryId ORDER BY dueDate ASC")
    fun getTasksByCategory(categoryId: Int): Flow<List<Task>>

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    fun getTaskWithSubtasks(taskId: Int): Flow<TaskWithSubtasks>
}