package ru.perelyginva.lessonroom.presentation.di


import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.perelyginva.lessonroom.data.db.DatabaseShop
import ru.perelyginva.lessonroom.data.repositories.CategoryRepository
import ru.perelyginva.lessonroom.data.repositories.MovieRepository
import ru.perelyginva.lessonroom.domain.repository.CategoriesCall
import ru.perelyginva.lessonroom.domain.repository.MoviesCall
import ru.perelyginva.lessonroom.domain.useCase.CategoriesUseCase
import ru.perelyginva.lessonroom.domain.useCase.MoviesUseCase
import ru.perelyginva.lessonroom.viewModels.CategoryViewModel
import ru.perelyginva.lessonroom.viewModels.MovieViewModel

val moduleMovies = module {
    moduleDBMovies

    single<MoviesCall> { MovieRepository(get()) }

    single { MoviesUseCase(get()) }

    viewModel { MovieViewModel(get()) }
}

val moduleCategory = module {

    moduleDBCategories

    single<CategoriesCall> { CategoryRepository(get()) }

    single { CategoriesUseCase(get()) }

    viewModel { CategoryViewModel(get()) }
}


val moduleDBMovies = module {

    single {
        Room.databaseBuilder(androidContext(),
            DatabaseShop::class.java, "dbMovies").build()
    }

    single { get<DatabaseShop>().movieDao }
}

val moduleDBCategories = module {
    single {
        Room.databaseBuilder(androidContext(),
            DatabaseShop::class.java, "dbCategory").build()
    }

    single { get<DatabaseShop>().categoryDao }
}