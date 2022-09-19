package ru.perelyginva.lessonroom.tabs

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.perelyginva.lessonroom.R
import ru.perelyginva.lessonroom.data.DatabaseShop
import ru.perelyginva.lessonroom.databinding.FragmentPanelEditProductBinding
import ru.perelyginva.lessonroom.repositories.MovieRepository
import ru.perelyginva.lessonroom.viewModels.MovieFactory
import ru.perelyginva.lessonroom.viewModels.MovieViewModel


class PanelEditMovieFragment : BottomSheetDialogFragment(), View.OnKeyListener,
    View.OnClickListener {

    private var binding: FragmentPanelEditProductBinding? = null
    private var movieRepository: MovieRepository? = null
    private var movieViewModel: MovieViewModel? = null
    private var movieFactory: MovieFactory? = null
    private var idProduct: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPanelEditProductBinding.inflate(inflater, container, false)

        idProduct = arguments?.getString("idProduct")?.toInt()
        binding?.editNameProduct?.setText(arguments?.getString("nameProduct").toString())
        binding?.editCategoryProduct?.setText(arguments?.getString("categoryProduct").toString())
        binding?.editPriceProduct?.setText(arguments?.getString("priceProduct").toString())

        val productDao = DatabaseShop
            .getInstance((context as FragmentActivity).application).movieDao
        movieRepository = MovieRepository(productDao)
        movieFactory = MovieFactory(movieRepository!!)
        movieViewModel = ViewModelProvider(this,
            movieFactory!!)[MovieViewModel::class.java]

        binding?.editNameProduct?.setOnKeyListener(this)
        binding?.editCategoryProduct?.setOnKeyListener(this)
        binding?.editPriceProduct?.setOnKeyListener(this)

        binding?.buttonEditProduct?.setOnClickListener(this)

        return binding?.root
    }

    override fun onKey(view: View?, keyCode: Int, event: KeyEvent?): Boolean {
        when (view?.id) {

            R.id.enterNameProduct -> {
                if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditNameProduct?.text = binding?.editNameProduct?.text
                    binding?.editNameProduct?.setText("")
                    return true
                }
            }

            R.id.enterCategoryProduct -> {
                if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditCategoryProduct?.text = binding?.editCategoryProduct?.text
                    binding?.editCategoryProduct?.setText("")
                    return true
                }
            }

            R.id.enterPriceProduct -> {
                if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditPriceProduct?.text = binding?.editPriceProduct?.text
                    binding?.editPriceProduct?.setText("")
                    return true
                }

            }
        }
        return false
    }

    override fun onClick(view: View?) {

        movieViewModel?.startUpdateProduct(
            idProduct.toString().toInt(),
            binding?.resEditNameProduct?.text?.toString()!!,
            binding?.resEditCategoryProduct?.text?.toString()!!,
            binding?.resEditPriceProduct?.text?.toString()!!)
        dismiss()

        (context as FragmentActivity).supportFragmentManager
            .beginTransaction().replace(R.id.content, TabMoviesFragment()).commit()
    }


}