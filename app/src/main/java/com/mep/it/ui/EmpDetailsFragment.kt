package com.mep.it.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mep.it.R
import com.mep.it.databinding.FragmentEmpDetailsBinding
import com.mep.it.model.EmployeeResponse
import com.mep.it.ui.adapters.MainAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmpDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEmpDetailsBinding
    private var selectedEmp = ArrayList<EmployeeResponse.Datum>()
    private lateinit var adapter: MainAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmpDetailsBinding.inflate(inflater, container, false)
        (activity as HomeActivity?)!!.binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        (activity as HomeActivity).binding.toolbar.title = "Selected Employees"
        (activity as HomeActivity).binding.bottomNavView.visibility = View.GONE
        (activity as HomeActivity).binding.btnNext.visibility = View.GONE
        (activity as HomeActivity).binding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            // perform whatever you want on back arrow click
            findNavController().popBackStack()
        })
        selectedEmp = ArrayList()
        adapter = MainAdapter(::onEmpItemClick)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvEmpList.adapter = adapter

        setInitialData()

    }

    private fun setInitialData() {
        val empData:ArrayList<EmployeeResponse.Datum> = arguments?.getSerializable("empList") as ArrayList<EmployeeResponse.Datum>
        
        empData?.let {
            adapter.submitList(empData)
        }
    }

    private fun onEmpItemClick(empData: EmployeeResponse.Datum) {


    }
}