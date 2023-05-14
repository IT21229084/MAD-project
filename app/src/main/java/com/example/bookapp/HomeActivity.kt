package com.example.bookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.bookapp.databinding.ActivityHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

//    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var shopArrayList: ArrayList<ModelShop>
    private lateinit var adapterShop: AdapterShop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadShops()

        binding.searchEt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    adapterShop.filter.filter(s)
                }
                catch (e: Exception){

                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

    }

    //load all shops
    private fun loadShops() {
        shopArrayList = ArrayList()

        //get from firebase
        val ref = FirebaseDatabase.getInstance().getReference("Shops")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                shopArrayList.clear()
                for (ds in snapshot.children){
                    val model = ds.getValue(ModelShop::class.java)
                    shopArrayList.add(model!!)
                }
                adapterShop = AdapterShop(this@HomeActivity, shopArrayList)
                binding.categoriesRv.adapter = adapterShop
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}