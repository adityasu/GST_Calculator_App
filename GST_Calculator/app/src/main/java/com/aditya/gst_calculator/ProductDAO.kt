package com.aditya.gst_calculator

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDAO {
    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("select * from PRODUCT_DETAILS")
    fun getAllProducts() : LiveData<List<Product>>

}