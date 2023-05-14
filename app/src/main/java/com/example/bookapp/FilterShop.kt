package com.example.bookapp

import android.graphics.ColorSpace.Model
import android.widget.Filter

class FilterShop: Filter {

    private var filterList: ArrayList<ModelShop>

    private var adapterShop: AdapterShop

    constructor(filterList: ArrayList<ModelShop>, adapterShop: AdapterShop) : super() {
        this.filterList = filterList
        this.adapterShop = adapterShop
    }

    //filtering function
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint = constraint
        var results = FilterResults()

        if (constraint != null && constraint.isNotEmpty()){
            //avoid case sensitivity
            constraint = constraint.toString().uppercase()
            var filteredModels: ArrayList<ModelShop> = ArrayList()
            for (i in 0 until filterList.size){
                if (filterList[i].shopName.uppercase().contains(constraint)){
                    filteredModels.add(filterList[i])
                }
            }
            results.count = filteredModels.size
            results.values = filteredModels
        }
        else{
            results.count = filterList.size
            results.values = filterList
        }
        return results
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults) {
        adapterShop.shopArrayList = results.values as ArrayList<ModelShop>
        adapterShop.notifyDataSetChanged()
    }
}