package ru.perelyginva.lessonroom.data.repositories

import androidx.lifecycle.LiveData
import ru.perelyginva.lessonroom.data.db.CategoryDao
import ru.perelyginva.lessonroom.data.db.models.CategoryModel
import ru.perelyginva.lessonroom.domain.repository.CategoriesCall

// в качестве конструктора передаем интерфейс categoryDao
class CategoryRepository(private val categoryDao: CategoryDao) : CategoriesCall {

    override fun loadCategories(): LiveData<List<CategoryModel>> {

        return categoryDao.getAllCategory()
    }

    override suspend fun insertCategory(categoryModel: CategoryModel) {
        categoryDao.insertCategory(categoryModel)
    }

    override suspend fun updateCategory(categoryModel: CategoryModel) {
        categoryDao.updateCategory(categoryModel)
    }

    override suspend fun deleteCategory(categoryModel: CategoryModel) {
        categoryDao.deleteCategory(categoryModel)
    }

    override suspend fun deleteAllCategory() {
        categoryDao.deleteAllCategories()
    }
}