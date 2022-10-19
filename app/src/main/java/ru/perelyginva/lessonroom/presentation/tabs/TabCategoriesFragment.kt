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
import ru.perelyginva.lessonroom.databinding.FragmentTabCategoriesBinding
import ru.perelyginva.lessonroom.data.db.models.CategoryModel
import ru.perelyginva.lessonroom.presentation.tabs.adapter.CategoryAdapter
import ru.perelyginva.lessonroom.viewModels.CategoryViewModel


class TabCategoriesFragment : Fragment() {

    private var binding: FragmentTabCategoriesBinding? = null
    private var categoryAdapter: CategoryAdapter? = null
    private val categoryViewModel: CategoryViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTabCategoriesBinding.inflate(inflater, container, false)


        initRecyclerCategories()


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
        displayCategories()
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