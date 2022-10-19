package ru.perelyginva.lessonroom.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.perelyginva.lessonroom.presentation.di.moduleCategory
import ru.perelyginva.lessonroom.presentation.di.moduleDBCategories
import ru.perelyginva.lessonroom.presentation.di.moduleDBMovies
import ru.perelyginva.lessonroom.presentation.di.moduleMovies

class App {
    class App : Application() {

        override fun onCreate() {
            super.onCreate()

            startKoin {
                // Koin Android logger
                androidLogger()
                //inject Android context
                androidContext(this@App)

                modules(moduleMovies, moduleCategory, moduleDBCategories, moduleDBMovies)

            }

        }
    }
}
