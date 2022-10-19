package ru.perelyginva.lessonroom.domain.repository

import androidx.lifecycle.LiveData
import ru.perelyginva.lessonroom.data.db.models.CategoryModel

interface CategoriesCall {

    fun loadCategories(): LiveData<List<CategoryModel>>

    suspend fun insertCategory(categoryModel: CategoryModel)

    suspend fun updateCategory(categoryModel: CategoryModel)

    suspend fun deleteCategory(categoryModel: CategoryModel)

    suspend fun deleteAllCategory()
}