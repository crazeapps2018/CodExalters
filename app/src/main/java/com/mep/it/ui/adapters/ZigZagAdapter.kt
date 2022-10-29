package com.mep.it.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mep.it.R
import com.mep.it.databinding.RowZigZagBinding

class ZigZagAdapter() : RecyclerView.Adapter<ZigZagAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowZigZagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    class MyViewHolder(val binding: RowZigZagBinding) : ViewHolder(binding.root)


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            val mod = adapterPosition % 4
           if(mod == 0 || mod == 3)
                binding.rootL.setBackgroundColor(binding.rootL.resources.getColor(R.color.black))
            else
                binding.rootL.setBackgroundColor(binding.rootL.resources.getColor(R.color.white))

        }
    }

    override fun getItemCount(): Int {
        return 20
    }


}