package ru.perelyginva.lessonroom.presentation.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.perelyginva.lessonroom.databinding.FragmentTabFiltersBinding
import ru.perelyginva.lessonroom.data.db.models.MovieModel
import ru.perelyginva.lessonroom.presentation.tabs.adapter.MovieAdapter
import ru.perelyginva.lessonroom.viewModels.MovieViewModel


class TabFiltersFragment : Fragment() {

    private var binding: FragmentTabFiltersBinding? = null
    private val movieViewModel: MovieViewModel by viewModel()
    private var movieAdapter: MovieAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTabFiltersBinding.inflate(inflater, container, false)

        initRecyclerFilterProduct()


        return binding?.root
    }

    private fun initRecyclerFilterProduct() {
        binding?.recyclerFilter?.layoutManager = LinearLayoutManager(context)
        movieAdapter = MovieAdapter(
            { movieModel: MovieModel -> deleteProduct(movieModel) },
            { movieModel: MovieModel -> editProduct(movieModel) })
        binding?.recyclerFilter?.adapter = movieAdapter

        displayFilterProduct()
    }

    private fun displayFilterProduct() {

        movieViewModel?.getFilter("одежда", "5000")?.observe(viewLifecycleOwner,
            Observer {
                movieAdapter?.setList(it)
                movieAdapter?.notifyDataSetChanged()
            })
    }

    private fun deleteProduct(movieModel: MovieModel) {
        movieViewModel.deleteProduct(movieModel)
    }

    private fun editProduct(movieModel: MovieModel) {

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