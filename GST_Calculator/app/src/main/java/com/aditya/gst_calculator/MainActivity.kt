package com.aditya.gst_calculator

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var spinner1 : Spinner
    lateinit var add_to_cart_btn : Button
    lateinit var results_textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val productViewModel : ProductViewModel =ViewModelProvider(
            this, CustomViewModelFactory(
                application
            )
        ).get(ProductViewModel::class.java)
        spinner1 = findViewById(R.id.productSpinner)
        add_to_cart_btn = findViewById(R.id.add_to_cart_btn)
        results_textView = findViewById(R.id.resultsTextView)

        productViewModel.getProducts().observe(this, Observer { it ->
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, it)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1.adapter = spinnerAdapter
        })

        add_to_cart_btn.setOnClickListener(View.OnClickListener {
            var product: Product = getSelectedProduct(it)
            var stateGST = (product.price * product.state_GST) / 100
            var centralGST = (product.price * product.central_GST) / 100
            var totalGST = stateGST + centralGST
            results_textView.text = ("Cost Breakup : " +
                                    "\n" +
                                    "State GST : " + product.state_GST + "%" + " = " + (stateGST) +
                                    "\n" +
                                    "Central GST : " + product.central_GST+ "%" + " = " + (centralGST)+
                                    "\n" +
                                    "Total GST : " + product.total_GST + "%" + " = " + (totalGST) +
                                    "\n" +
                                    "Price before GST : " + product.price +
                                    "\n" +
                                    "Price After GST : " + (product.price + totalGST))
        })
    }

    private fun getSelectedProduct(view: View) : Product{
        return spinner1.selectedItem as Product
    }

}