package com.example.bookapp

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookapp.databinding.RowVehicleBinding

class AdapterVehicle: RecyclerView.Adapter<AdapterVehicle.HolderVehicle> {

    private var context: Context
    public var vehicleArrayList: ArrayList<ModelVehicle>

    private lateinit var binding: RowVehicleBinding

    constructor(context: Context, vehicleArrayList: ArrayList<ModelVehicle>) : super() {
        this.context = context
        this.vehicleArrayList = vehicleArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderVehicle {
        binding = RowVehicleBinding.inflate(LayoutInflater.from(context), parent, false)

        return HolderVehicle(binding.root)
    }

    override fun getItemCount(): Int {
        return vehicleArrayList.size
    }

    override fun onBindViewHolder(holder: HolderVehicle, position: Int) {
        //get
        val model = vehicleArrayList[position]
        val vehicleID = model.vehicleID
        val vehicleName = model.vehicleName
        val vehicleDescription = model.vehicleDescription
        val vehicleCount = model.vehicleCount
        val vehicleTypeID = model.vehicleTypeID
        val imagePath = model.imagePath
        val shopID = model.shopID

        //set
        holder.vehicleNameTv.text = vehicleName
        holder.countTv.text = vehicleCount.toString()

        MyApplication.loadShop(shopID, holder.vehicleNameTv)

    }

    inner class HolderVehicle(itemView: View): RecyclerView.ViewHolder(itemView){
        val vehicleNameTv = binding.vehicleNameTv
        val countTv = binding.countTv
    }
}