package com.aditya.gst_calculator

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "product_details")
data class Product(@PrimaryKey var ID : Int = 0,
                   var product_name: String = "",
                   var state_GST : Double = 0.0,
                   var central_GST: Double = 0.0,
                   var total_GST : Double = 0.0,
                   var price : Double = 0.0) {

    override fun toString(): String {
        return product_name
    }
}