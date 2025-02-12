package com.example.loginfirebaseapp.data.repository

import com.example.loginfirebaseapp.data.dao.TaskDao
import com.example.loginfirebaseapp.model.Task
import com.example.loginfirebaseapp.model.relation.TaskWithSubtasks
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun update(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun delete(task: Task) {
        taskDao.deleteTask(task)
    }

    fun getTasksByCategory(categoryId: Int): Flow<List<Task>> {
        return taskDao.getTasksByCategory(categoryId)
    }

    fun getTaskWithSubtasks(taskId: Int): Flow<TaskWithSubtasks> {
        return taskDao.getTaskWithSubtasks(taskId)
    }
}