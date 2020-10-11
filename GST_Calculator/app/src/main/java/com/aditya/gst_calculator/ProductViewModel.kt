package com.aditya.gst_calculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

open class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private var repository : ProductRepo = ProductRepo(application)
    var allProducts : LiveData<List<Product>>
    init {
        allProducts = repository.allProducts
    }

    fun getProducts() : LiveData<List<Product>> {
        return allProducts;
    }

}
