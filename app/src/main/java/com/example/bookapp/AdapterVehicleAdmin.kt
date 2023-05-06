package com.example.bookapp

import android.app.AlertDialog
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
import com.example.bookapp.databinding.ActivityVehicleListBinding
import com.example.bookapp.databinding.RowVehicleBinding
import android.content.Intent

class AdapterVehicleAdmin: RecyclerView.Adapter<AdapterVehicleAdmin.HolderVehicleAdmin> {

    private val context: Context
    public var vehicleAdminArrayList: ArrayList<ModelVehicleAdmin>

    private lateinit var binding: RowVehicleBinding

    constructor(context: Context, vehicleAdminArrayList: ArrayList<ModelVehicleAdmin>) : super() {
        this.context = context
        this.vehicleAdminArrayList = vehicleAdminArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderVehicleAdmin {

        binding = RowVehicleBinding.inflate(LayoutInflater.from(context), parent, false)

        return HolderVehicleAdmin(binding.root)
    }

    override fun onBindViewHolder(holder: HolderVehicleAdmin, position: Int) {
        //get
        val model = vehicleAdminArrayList[position]
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

        //more btn
        holder.moreBtn.setOnClickListener{
            moreOptionsDialog(model, holder)
        }
    }

    private fun moreOptionsDialog(model: ModelVehicleAdmin, holder: AdapterVehicleAdmin.HolderVehicleAdmin) {
        //get vehicle details
        val vehicleID = model.vehicleID
        val vName = model.vehicleName
        val vDescription = model.vehicleDescription
        val vCount = model.vehicleCount

        val options = arrayOf("Edit", "Delete")

        //popup
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Chose Option")
            .setItems(options){dialog, position->
                if (position==0){
                    //edit
                    val intent = Intent(context, VehicleEditActivity::class.java)
                    intent.putExtra("vehicleID", vehicleID)
                    context.startActivity(intent)
                }
                else if (position == 1){
                    //delete
                    MyApplication.deleteVehicle(context, vehicleID, vName)
                }
            }
            .show()
    }

    override fun getItemCount(): Int {
        return vehicleAdminArrayList.size
    }

    inner class HolderVehicleAdmin(itemView: View): RecyclerView.ViewHolder(itemView){
        var vehicleNameTv: TextView = binding.vehicleNameTv
        val countTv: TextView = binding.countTv
        val moreBtn = binding.moreBtn
    }
}