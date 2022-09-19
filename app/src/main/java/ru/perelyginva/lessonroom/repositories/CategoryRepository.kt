package ru.perelyginva.lessonroom.repositories

import ru.perelyginva.lessonroom.data.CategoryDao
import ru.perelyginva.lessonroom.models.CategoryModel

// в качестве конструктора передаем интерфейс categoryDao
class CategoryRepository(private val categoryDao: CategoryDao) {

    val categories = categoryDao.getAllCategory()// получаем все категории

    suspend fun insertCategory(categoryModel: CategoryModel){
        categoryDao.insertCategory(categoryModel)
    }

    suspend fun updateCategory(categoryModel: CategoryModel){
        categoryDao.updateCategory(categoryModel)
    }

    suspend fun deleteCategory(categoryModel: CategoryModel){
        categoryDao.deleteCategory(categoryModel)
    }

    suspend fun deleteAllCategory(){
        categoryDao.deleteAllCategories()
    }
}