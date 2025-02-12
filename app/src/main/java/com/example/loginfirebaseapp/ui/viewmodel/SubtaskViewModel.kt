package com.example.loginfirebaseapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginfirebaseapp.data.repository.SubtaskRepository
import com.example.loginfirebaseapp.model.Subtask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SubtaskViewModel(private val repository: SubtaskRepository) : ViewModel() {

    fun insert(subtask: Subtask) {
        viewModelScope.launch {
            repository.insert(subtask)
        }
    }

    fun update(subtask: Subtask) {
        viewModelScope.launch {
            repository.update(subtask)
        }
    }

    fun delete(subtask: Subtask) {
        viewModelScope.launch {
            repository.delete(subtask)
        }
    }

    fun getSubtasksByTask(taskId: Int): Flow<List<Subtask>> {
        return repository.getSubtasksByTask(taskId)
    }
}