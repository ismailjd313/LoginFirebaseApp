package com.example.loginfirebaseapp.data.repository

import com.example.loginfirebaseapp.data.dao.SubtaskDao
import com.example.loginfirebaseapp.model.Subtask
import kotlinx.coroutines.flow.Flow

class SubtaskRepository(private val subtaskDao: SubtaskDao) {

    suspend fun insert(subtask: Subtask) {
        subtaskDao.insertSubtask(subtask)
    }

    suspend fun update(subtask: Subtask) {
        subtaskDao.updateSubtask(subtask)
    }

    suspend fun delete(subtask: Subtask) {
        subtaskDao.deleteSubtask(subtask)
    }

    fun getSubtasksByTask(taskId: Int): Flow<List<Subtask>> {
        return subtaskDao.getSubtasksByTask(taskId)
    }
}