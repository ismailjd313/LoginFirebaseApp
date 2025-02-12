package com.example.loginfirebaseapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.loginfirebaseapp.model.Category
import com.example.loginfirebaseapp.model.relation.CategoryWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM categories ORDER BY categoryId ASC")
    fun getAllCategories(): Flow<List<Category>>

    @Transaction
    @Query("SELECT * FROM categories WHERE categoryId = :categoryId")
    fun getCategoryWithTasks(categoryId: Int): Flow<CategoryWithTasks>
}