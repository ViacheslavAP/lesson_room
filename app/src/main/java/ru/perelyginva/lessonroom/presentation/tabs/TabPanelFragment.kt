package ru.perelyginva.lessonroom.presentation.tabs

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.perelyginva.lessonroom.R
import ru.perelyginva.lessonroom.databinding.FragmentTabPanelBinding
import ru.perelyginva.lessonroom.viewModels.CategoryViewModel
import ru.perelyginva.lessonroom.viewModels.MovieViewModel


class TabPanelFragment : Fragment(), View.OnClickListener, View.OnKeyListener {

    private var binding: FragmentTabPanelBinding? = null
    private val categoryViewModel: CategoryViewModel by viewModel()
    private val movieViewModel: MovieViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentTabPanelBinding.inflate(inflater, container, false)

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
                categoryViewModel.startInsertCategory(
                    binding?.buttonAddCategoryClothes?.text?.toString()!!)
            }

            R.id.buttonAddCategoryShoes -> {
                categoryViewModel.startInsertCategory(
                    binding?.buttonAddCategoryShoes?.text?.toString()!!)
            }

            R.id.buttonAddCategoryAccessories -> {
                categoryViewModel.startInsertCategory(
                    binding?.buttonAddCategoryAccessories?.text?.toString()!!)
            }

            R.id.buttonAddProduct -> {

                movieViewModel.startInsertProduct(
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