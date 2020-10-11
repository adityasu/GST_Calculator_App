package com.aditya.gst_calculator

import android.app.Application
import androidx.lifecycle.LiveData


class ProductRepo(application: Application) {

    lateinit var productDAO : ProductDAO
    lateinit var allProducts : LiveData<List<Product>>
    lateinit var  product: Product
    init {
        val db = ProductDatabase.getInMemoryDatabase(application)
        if (db != null) {
            productDAO = db.productDAO()
        }
        allProducts = productDAO.getAllProducts()
    }
}