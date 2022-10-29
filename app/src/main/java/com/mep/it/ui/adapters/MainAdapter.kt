package com.mep.it.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mep.it.databinding.RowEmpBinding
import com.mep.it.model.EmployeeResponse

class MainAdapter(private val onNoteClicked: (EmployeeResponse.Datum) -> Unit) :
    ListAdapter<EmployeeResponse.Datum, MainAdapter.EmpViewHolder>(ComparatorDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpViewHolder {
        val binding = RowEmpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmpViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmpViewHolder, position: Int) {
        val note = getItem(position)

        note?.let {
            holder.bind(it)
        }
    }

    inner class EmpViewHolder(private val binding: RowEmpBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: EmployeeResponse.Datum) {

            binding.empData = data
            binding.checkbox.setOnCheckedChangeListener { p0, isChecked ->
                if (isChecked){
                    onNoteClicked(data)
                }
            }


        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<EmployeeResponse.Datum>() {
        override fun areItemsTheSame(oldItem: EmployeeResponse.Datum, newItem: EmployeeResponse.Datum): Boolean {
            return oldItem.empId == newItem.empId
        }

        override fun areContentsTheSame(oldItem: EmployeeResponse.Datum, newItem: EmployeeResponse.Datum): Boolean {
            return oldItem.equals(newItem)
        }
    }
}