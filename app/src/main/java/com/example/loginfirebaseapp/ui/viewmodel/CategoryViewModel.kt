package com.example.loginfirebaseapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginfirebaseapp.data.repository.CategoryRepository
import com.example.loginfirebaseapp.model.Category
import com.example.loginfirebaseapp.model.relation.CategoryWithTasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    val allCategories: Flow<List<Category>> = repository.allCategories

    fun insert(category: Category) {
        viewModelScope.launch {
            repository.insert(category)
        }
    }

    fun update(category: Category) {
        viewModelScope.launch {
            repository.update(category)
        }
    }

    fun delete(category: Category) {
        viewModelScope.launch {
            repository.delete(category)
        }
    }

    fun getCategoryWithTasks(categoryId: Int): Flow<CategoryWithTasks> {
        return repository.getCategoryWithTasks(categoryId)
    }
}