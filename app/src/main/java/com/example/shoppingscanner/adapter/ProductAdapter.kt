package com.example.shoppingscanner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingscanner.databinding.ProductItemBinding
import com.example.shoppingscanner.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter (private val data: HashMap<Product, Int>):
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    lateinit var binding: ProductItemBinding

    class ProductViewHolder (view: ProductItemBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            holder.itemView.apply {
                binding.product = data.entries.elementAt(position).key
                val productImage = data.entries.elementAt(position).key.images!![0]
                Picasso.get().load(productImage).into(binding.productImage)
            }
    }
}