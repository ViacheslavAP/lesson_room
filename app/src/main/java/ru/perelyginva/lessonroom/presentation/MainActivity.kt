package ru.perelyginva.lessonroom.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import ru.perelyginva.lessonroom.R
import ru.perelyginva.lessonroom.databinding.ActivityMainBinding
import ru.perelyginva.lessonroom.presentation.tabs.TabCategoriesFragment
import ru.perelyginva.lessonroom.presentation.tabs.TabFiltersFragment
import ru.perelyginva.lessonroom.presentation.tabs.TabPanelFragment
import ru.perelyginva.lessonroom.presentation.tabs.TabMoviesFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        supportFragmentManager.beginTransaction().replace(R.id.content, TabPanelFragment()).commit()

        binding?.bottomNav?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.panelItemBottomNav ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, TabPanelFragment()).commit()

                R.id.categoryProductsItemBottomNav ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, TabMoviesFragment()).commit()

                R.id.catalogClothesItemBottomNav ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, TabFiltersFragment()).commit()

                R.id.catalogCategoriesItemBottomNav ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, TabCategoriesFragment()).commit()

            }
            return@setOnItemSelectedListener true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.bottom_menu, menu)
        return true
    }
}