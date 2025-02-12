package com.example.loginfirebaseapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.loginfirebaseapp.model.Subtask
import kotlinx.coroutines.flow.Flow

@Dao
interface SubtaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubtask(subtask: Subtask)

    @Update
    suspend fun updateSubtask(subtask: Subtask)

    @Delete
    suspend fun deleteSubtask(subtask: Subtask)

    @Query("SELECT * FROM subtasks WHERE taskOwnerId = :taskId ORDER BY subtaskId ASC")
    fun getSubtasksByTask(taskId: Int): Flow<List<Subtask>>
}