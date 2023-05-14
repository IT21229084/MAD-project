package com.example.user.Activities

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.user.Model.Payment_all
import com.example.user.R
import com.google.firebase.database.FirebaseDatabase

class paymentAdapter (private val paymentList: ArrayList<Payment_all>) : RecyclerView.Adapter<paymentAdapter.holder>(){
    
    private val payment = paymentList

    class holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var edit: ImageView
        var delete: ImageView
        var reason: TextView
        var card: TextView
        var amount: TextView
        var type: TextView
        var name: TextView

        init {
            reason = itemView.findViewById<View>(R.id.list_reason) as TextView
            card = itemView.findViewById<View>(R.id.list_card) as TextView
            amount = itemView.findViewById<View>(R.id.list_amount) as TextView
            this.type = itemView.findViewById<View>(R.id.list_type) as TextView
            name = itemView.findViewById<View>(R.id.list_name) as TextView
            edit = itemView.findViewById<View>(R.id.editicon) as ImageView
            delete = itemView.findViewById<View>(R.id.deleteicon) as ImageView
        }
    }
    
    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.reason.setText(payment[position].getReason())
        holder.name.setText(payment[position].getName())
        holder.card.setText(payment[position].getCard())
        holder.type.setText(payment[position].getType())
        holder.amount.setText(payment[position].getAmount().toString())
        holder.edit.setOnClickListener(View.OnClickListener { view ->
            val i = Intent(view.context, edit_payment::class.java)
            i.putExtra("reason", payment[position].getReason())
            i.putExtra("name", payment[position].getName())
            i.putExtra("card", payment[position].getCard())
            i.putExtra("ccv", payment[position].getCcv().toString())
            i.putExtra("type", payment[position].getType())
            i.putExtra("amount", payment[position].getAmount().toString())
            i.putExtra("month", payment[position].getMonth().toString())
            i.putExtra("year", payment[position].getYear().toString())
            i.putExtra("id", payment[position].getId())
            view.context.startActivity(i)
        })
        holder.delete.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.getContext())
            builder.setTitle("Delete Todo")
            builder.setMessage("Delete...?")
            builder.setPositiveButton(
                "Yes"
            ) { dialogInterface, i ->
                FirebaseDatabase.getInstance().reference.child("payments")
                    .child(payment[position].getId().toString()).removeValue()
                paymentList.clear()
            }
            builder.setNegativeButton(
                "No"
            ) { dialogInterface, i -> }
            builder.show()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.singlerow, parent, false)
        return holder(view)
    }

    override fun getItemCount(): Int {
        return paymentList.size
    }
}