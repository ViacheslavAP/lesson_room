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
import ru.perelyginva.lessonroom.databinding.FragmentTabCategoriesBinding
import ru.perelyginva.lessonroom.models.CategoryModel
import ru.perelyginva.lessonroom.repositories.CategoryRepository
import ru.perelyginva.lessonroom.tabs.adapter.CategoryAdapter
import ru.perelyginva.lessonroom.viewModels.CategoryFactory
import ru.perelyginva.lessonroom.viewModels.CategoryViewModel


class TabCategoriesFragment : Fragment() {

    private var binding: FragmentTabCategoriesBinding? = null
    private var categoryRepository: CategoryRepository? = null
    private var categoryViewModel: CategoryViewModel? = null
    private var categoryFactory: CategoryFactory? = null
    private var categoryAdapter: CategoryAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTabCategoriesBinding.inflate(inflater, container, false)

        val categoryDao = DatabaseShop
            .getInstance((context as FragmentActivity).application).categoryDao

        categoryRepository = CategoryRepository(categoryDao)
        categoryFactory = CategoryFactory(categoryRepository!!)
        categoryViewModel = ViewModelProvider(this,
            categoryFactory!!)[CategoryViewModel::class.java]

        initRecyclerCategories()
        displayCategories()

        binding?.deleteAllCategories?.setOnClickListener(View.OnClickListener {
            categoryViewModel?.deleteAllCategory()
        })

        return binding?.root
    }

    private fun displayCategories() {
        categoryViewModel?.categories?.observe(viewLifecycleOwner, Observer {
            categoryAdapter?.setList(it)
            categoryAdapter?.notifyDataSetChanged()

        })
    }

    private fun initRecyclerCategories() {
        binding?.recyclerCategories?.layoutManager = LinearLayoutManager(context)
        categoryAdapter = CategoryAdapter(
            { categoryModel: CategoryModel -> deleteCategory(categoryModel) },
            { categoryModel: CategoryModel -> editCategory(categoryModel) }
        )
        binding?.recyclerCategories?.adapter = categoryAdapter

    }

    private fun deleteCategory(categoryModel: CategoryModel) {

        categoryViewModel?.deleteCategory(categoryModel)

    }

    private fun editCategory(categoryModel: CategoryModel) {

        val panelCategory = PanelEditCategoryFragment()
        val parameters = Bundle()
        parameters.putString("idCategory", categoryModel.id.toString())
        parameters.putString("nameCategory", categoryModel.name)
        panelCategory.arguments = parameters
        panelCategory.show((context as FragmentActivity).supportFragmentManager, "editCategory")
    }

}