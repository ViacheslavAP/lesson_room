package ru.perelyginva.lessonroom.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.perelyginva.lessonroom.data.db.models.MovieModel
@Dao
interface MovieDao {

    @Insert
    suspend fun insertProduct(movieModel: MovieModel)

    @Update
    suspend fun updateProduct(movieModel: MovieModel)

    @Delete
    suspend fun deleteProduct(movieModel: MovieModel)

    @Query("DELETE FROM product_data_table")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM product_data_table")
    fun getAllProducts(): LiveData<List<MovieModel>>

    @Query("SELECT * FROM product_data_table WHERE product_category =:nameCategory OR product_price =:priceProduct")
    fun getFilter(nameCategory: String, priceProduct: String): LiveData<List<MovieModel>>

}