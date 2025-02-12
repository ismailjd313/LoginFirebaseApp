package com.example.loginfirebaseapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginfirebaseapp.data.repository.TaskRepository
import com.example.loginfirebaseapp.model.Task
import com.example.loginfirebaseapp.model.relation.TaskWithSubtasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    fun insert(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    fun getTasksByCategory(categoryId: Int): Flow<List<Task>> {
        return repository.getTasksByCategory(categoryId)
    }

    fun getTaskWithSubtasks(taskId: Int): Flow<TaskWithSubtasks> {
        return repository.getTaskWithSubtasks(taskId)
    }
}