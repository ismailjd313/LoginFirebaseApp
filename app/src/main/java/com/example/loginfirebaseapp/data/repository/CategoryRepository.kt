package com.example.loginfirebaseapp.data.repository

import com.example.loginfirebaseapp.data.dao.CategoryDao
import com.example.loginfirebaseapp.model.Category
import com.example.loginfirebaseapp.model.relation.CategoryWithTasks
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao) {

    val allCategories: Flow<List<Category>> = categoryDao.getAllCategories()

    suspend fun insert(category: Category) {
        categoryDao.insertCategory(category)
    }

    suspend fun update(category: Category) {
        categoryDao.updateCategory(category)
    }

    suspend fun delete(category: Category) {
        categoryDao.deleteCategory(category)
    }

    fun getCategoryWithTasks(categoryId: Int): Flow<CategoryWithTasks> {
        return categoryDao.getCategoryWithTasks(categoryId)
    }
}