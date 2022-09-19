package ru.perelyginva.lessonroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.perelyginva.lessonroom.models.CategoryModel
import ru.perelyginva.lessonroom.models.MovieModel

@Database(entities = [CategoryModel::class, MovieModel::class], version = 1)
abstract class DatabaseShop: RoomDatabase() {

    //указываем столько интерфейсов сколько будет использованно!
    abstract val categoryDao: CategoryDao
    abstract val movieDao: MovieDao

    companion object{
         @Volatile
         private var INSTANCE: ru.perelyginva.lessonroom.data.DatabaseShop? = null

        fun getInstance(context: Context): ru.perelyginva.lessonroom.data.DatabaseShop{
            synchronized(this){

                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ru.perelyginva.lessonroom.data.DatabaseShop::class.java,
                        "database" // имя бады данных
                    ).build()
                }
                return instance
            }
        }
    }
}