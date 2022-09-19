package ru.perelyginva.lessonroom.tabs

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import ru.perelyginva.lessonroom.R
import ru.perelyginva.lessonroom.data.DatabaseShop
import ru.perelyginva.lessonroom.databinding.FragmentTabPanelBinding
import ru.perelyginva.lessonroom.repositories.CategoryRepository
import ru.perelyginva.lessonroom.repositories.MovieRepository
import ru.perelyginva.lessonroom.viewModels.CategoryFactory
import ru.perelyginva.lessonroom.viewModels.CategoryViewModel
import ru.perelyginva.lessonroom.viewModels.MovieFactory
import ru.perelyginva.lessonroom.viewModels.MovieViewModel


class TabPanelFragment : Fragment(), View.OnClickListener, View.OnKeyListener {

    private var binding: FragmentTabPanelBinding? = null
    private var categoryRepository: CategoryRepository? = null
    private var categoryViewModel: CategoryViewModel? = null
    private var categoryFactory: CategoryFactory? = null

    private var movieRepository: MovieRepository? = null
    private var movieViewModel: MovieViewModel? = null
    private var movieFactory: MovieFactory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTabPanelBinding.inflate(inflater, container, false)

        val categoryDao = DatabaseShop
            .getInstance((context as FragmentActivity).application).categoryDao

        categoryRepository = CategoryRepository(categoryDao)
        categoryFactory = CategoryFactory(categoryRepository!!)
        categoryViewModel = ViewModelProvider(this,
            categoryFactory!!)[CategoryViewModel::class.java]

        val productDao = DatabaseShop
            .getInstance((context as FragmentActivity).application).movieDao
        movieRepository = MovieRepository(productDao)
        movieFactory = MovieFactory(movieRepository!!)
        movieViewModel = ViewModelProvider(this,
            movieFactory!!)[MovieViewModel::class.java]

        binding?.enterNameProduct?.setOnKeyListener(this)
        binding?.enterCategoryProduct?.setOnKeyListener(this)
        binding?.enterPriceProduct?.setOnKeyListener(this)

        binding?.buttonAddProduct?.setOnClickListener(this)

        binding?.buttonAddCategoryClothes?.setOnClickListener(this)
        binding?.buttonAddCategoryShoes?.setOnClickListener(this)
        binding?.buttonAddCategoryAccessories?.setOnClickListener(this)

        return binding?.root
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.buttonAddCategoryClothes -> {
                categoryViewModel?.startInsertCategory(
                    binding?.buttonAddCategoryClothes?.text?.toString()!!)
            }

            R.id.buttonAddCategoryShoes -> {
                categoryViewModel?.startInsertCategory(
                    binding?.buttonAddCategoryShoes?.text?.toString()!!)
            }

            R.id.buttonAddCategoryAccessories -> {
                categoryViewModel?.startInsertCategory(
                    binding?.buttonAddCategoryAccessories?.text?.toString()!!)
            }

            R.id.buttonAddProduct -> {

                movieViewModel?.startInsertProduct(
                    binding?.resEnterNameProduct?.text?.toString()!!,
                    binding?.resEnterCategoryProduct?.text?.toString()!!,
                    binding?.resEnterPriceProduct?.text?.toString()!!
                )

                clearResEnterProduct()
            }
        }
    }

    private fun clearResEnterProduct() {
        binding?.resEnterNameProduct?.text = ""
        binding?.resEnterCategoryProduct?.text = ""
        binding?.resEnterPriceProduct?.text = ""
    }

    override fun onKey(view: View?, keyCode: Int, event: KeyEvent?): Boolean {

        when (view?.id) {
            R.id.enterNameProduct -> {
                if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterNameProduct?.text = binding?.enterNameProduct?.text
                    binding?.enterNameProduct?.setText("")
                    return true
                }
            }

            R.id.enterCategoryProduct -> {
                if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterCategoryProduct?.text = binding?.enterCategoryProduct?.text
                    binding?.enterCategoryProduct?.setText("")
                    return true
                }
            }

            R.id.enterPriceProduct -> {
                if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterPriceProduct?.text = binding?.enterPriceProduct?.text
                    binding?.enterPriceProduct?.setText("")
                    return true
                }
            }
        }

        return false
    }
}