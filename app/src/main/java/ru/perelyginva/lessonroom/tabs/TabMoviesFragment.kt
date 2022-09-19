package ru.perelyginva.lessonroom.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.perelyginva.lessonroom.data.DatabaseShop
import ru.perelyginva.lessonroom.databinding.FragmentTabProductsBinding
import ru.perelyginva.lessonroom.models.MovieModel
import ru.perelyginva.lessonroom.repositories.MovieRepository
import ru.perelyginva.lessonroom.tabs.adapter.MovieAdapter
import ru.perelyginva.lessonroom.viewModels.MovieFactory
import ru.perelyginva.lessonroom.viewModels.MovieViewModel


class TabMoviesFragment : Fragment() {

    private var binding: FragmentTabProductsBinding? = null
    private var movieRepository: MovieRepository? = null
    private var movieViewModel: MovieViewModel? = null
    private var movieFactory: MovieFactory? = null
    private var movieAdapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTabProductsBinding.inflate(inflater, container, false)

        val productDao = DatabaseShop
            .getInstance((context as FragmentActivity).application).movieDao
        movieRepository = MovieRepository(productDao)
        movieFactory = MovieFactory(movieRepository!!)
        movieViewModel = ViewModelProvider(this,
            movieFactory!!)[MovieViewModel::class.java]
        initRecyclerProduct()

        binding?.deleteAllProducts?.setOnClickListener(View.OnClickListener {
            movieViewModel?.deleteAllProduct()
        })

        return binding?.root
    }

    private fun initRecyclerProduct() {
        binding?.recyclerProducts?.layoutManager = LinearLayoutManager(context)
        movieAdapter = MovieAdapter(
            { movieModel: MovieModel -> deleteProduct(movieModel) },
            { movieModel: MovieModel -> editProduct(movieModel)})

        binding?.recyclerProducts?.adapter = movieAdapter
        displayProduct()
    }

    private fun displayProduct() {
        movieViewModel?.products?.observe(viewLifecycleOwner, Observer {
            movieAdapter?.setList(it)
            movieAdapter?.notifyDataSetChanged()

        })
    }

    private fun deleteProduct(movieModel: MovieModel){
        movieViewModel?.deleteProduct(movieModel)
    }

    private fun editProduct(movieModel: MovieModel){

        val panelEditProduct = PanelEditMovieFragment()
        val parameters = Bundle()
        parameters.putString("idProduct", movieModel.id.toString())
        parameters.putString("nameProduct", movieModel.name)
        parameters.putString("categoryProduct", movieModel.category)
        parameters.putString("priceProduct", movieModel.price)
        panelEditProduct.arguments = parameters
        panelEditProduct.show((context as FragmentActivity).supportFragmentManager, "editProduct")
    }

}