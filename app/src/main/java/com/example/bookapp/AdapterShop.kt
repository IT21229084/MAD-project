package com.example.bookapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.databinding.ActivityHomeBinding
import com.example.bookapp.databinding.RowCategoryBinding
import android.content.Intent
//import com.saviru.bookApp.databinding.RowShopBinding

class AdapterShop: RecyclerView.Adapter<AdapterShop.HolderShop>, Filterable {

    private val context: Context
    public var shopArrayList: ArrayList<ModelShop>
    private var filterList: ArrayList<ModelShop>

    private var filter: FilterShop? = null

    private lateinit var binding: RowCategoryBinding

    constructor(context: Context, shopArrayList: ArrayList<ModelShop>) {
        this.context = context
        this.shopArrayList = shopArrayList
        this.filterList = shopArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderShop {
        binding = RowCategoryBinding.inflate(LayoutInflater.from(context), parent, false)

        return HolderShop(binding.root)
    }

    override fun getItemCount(): Int {
        return shopArrayList.size
    }

    override fun onBindViewHolder(holder: HolderShop, position: Int) {
        //get
        val model = shopArrayList[position]
        val shopID = model.shopID
        val shopName = model.shopName
        val shopCity = model.shopCity
        val shopAddress = model.shopAddress
        val shopLocation = model.shopLocation

        //set
        holder.categoryTv.text = shopName

        //click shop
        holder.itemView.setOnClickListener {
            val intent = Intent(context, VehicleListActivity::class.java)
            intent.putExtra("shopID", shopID)
            intent.putExtra("shopName", shopName)
            context.startActivity(intent)
        }
    }

    inner class HolderShop(itemView: View): RecyclerView.ViewHolder(itemView){
        var categoryTv: TextView = binding.categoryTv
        var callBtn: ImageButton = binding.callBtn
    }

    override fun getFilter(): Filter {
        if (filter == null){
            filter = FilterShop(filterList, this)
        }
        return filter as FilterShop
    }


}