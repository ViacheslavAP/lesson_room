package ru.perelyginva.lessonroom.tabs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.perelyginva.lessonroom.databinding.CategoryItemBinding
import ru.perelyginva.lessonroom.models.CategoryModel

class CategoryAdapter(private val deleteCategory: (CategoryModel) -> Unit,
                      private val editCategory: (CategoryModel) -> Unit):
     RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoriesList = ArrayList<CategoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding: CategoryItemBinding = CategoryItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoriesList[position],deleteCategory, editCategory)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun setList(categories: List<CategoryModel>) {

        categoriesList.clear()
        categoriesList.addAll(categories)
    }

    class CategoryViewHolder(private val binding: CategoryItemBinding ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            categories: CategoryModel,
            deleteCategory: (CategoryModel) -> Unit,
            editCategory: (CategoryModel) -> Unit,
        ) {
            binding.idCategory.text = categories.id.toString()
            binding.nameCategory.text = categories.name

            binding.btnEditCategory.setOnClickListener ( View.OnClickListener {
                editCategory(categories)
            } )

            binding.btnDeleteCategory.setOnClickListener ( View.OnClickListener {
                deleteCategory(categories)
            } )
        }

    }
}