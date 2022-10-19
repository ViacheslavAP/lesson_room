package ru.perelyginva.lessonroom.domain.useCase

import androidx.lifecycle.LiveData
import ru.perelyginva.lessonroom.data.db.models.CategoryModel
import ru.perelyginva.lessonroom.domain.repository.CategoriesCall

class CategoriesUseCase(private val categoriesCall: CategoriesCall) {

      fun loadCategories(): LiveData<List<CategoryModel>> {

        return categoriesCall.loadCategories()
    }

     suspend fun insertCategory(categoryModel: CategoryModel) {
        categoriesCall.insertCategory(categoryModel)
    }

     suspend fun updateCategory(categoryModel: CategoryModel) {
        categoriesCall.updateCategory(categoryModel)
    }

     suspend fun deleteCategory(categoryModel: CategoryModel) {
        categoriesCall.deleteCategory(categoryModel)
    }

     suspend fun deleteAllCategory() {
        categoriesCall.deleteAllCategory()
    }
}