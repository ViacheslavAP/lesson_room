package ru.perelyginva.lessonroom.tabs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.perelyginva.lessonroom.databinding.ProductItemBinding
import ru.perelyginva.lessonroom.models.MovieModel

class MovieAdapter(
    private val deleteProduct: (MovieModel) -> Unit,
    private val editProduct: (MovieModel) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.ProductViewHolder>() {

    private val productList = ArrayList<MovieModel>()

    class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            productsModel: MovieModel,
            deleteProduct: (MovieModel) -> Unit,
            editProduct: (MovieModel) -> Unit,
        ) {
            binding.idProduct.text = productsModel.id.toString()
            binding.nameProduct.text = productsModel.name
            binding.priceProduct.text = productsModel.price
            binding.categoryProduct.text = productsModel.category

            binding.editProduct.setOnClickListener(View.OnClickListener {
                editProduct(productsModel)
                deleteProduct(productsModel)
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ProductItemBinding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position], deleteProduct, editProduct)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setList(products: List<MovieModel>) {
        productList.clear()
        productList.addAll(products)
    }
}