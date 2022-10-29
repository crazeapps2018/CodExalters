package com.mep.it.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.mep.it.R
import com.mep.it.databinding.FragmentHomeBinding
import com.mep.it.model.EmployeeResponse
import com.mep.it.ui.adapters.MainAdapter
import com.mep.it.util.NetworkResult
import com.mep.it.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: MainAdapter
    private var selectedEmp = ArrayList<EmployeeResponse.Datum>()
    private var allEmp = ArrayList<EmployeeResponse.Datum>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
          adapter = MainAdapter(::onEmpItemClick)
        (activity as HomeActivity?)!!.binding.toolbar.setNavigationIcon(null)

        (activity as HomeActivity).binding.toolbar.title = "Employees List"
        (activity as HomeActivity).binding.bottomNavView.visibility = View.VISIBLE
        (activity as HomeActivity).binding.btnNext.visibility = View.VISIBLE


        selectedEmp = ArrayList()
        allEmp = ArrayList()
        selectedEmp.clear()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindHandlers()
        bindObservers()
        viewModel.getEmployee()
        binding.rvEmpList.adapter = adapter
    }

    private fun bindHandlers() {

        binding.floatingButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("empList", allEmp)
            findNavController().navigate(R.id.action_homeFragment_to_mapFragment,bundle)

        }

        (activity as HomeActivity?)!!.binding.btnNext.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("empList", selectedEmp)
            findNavController().navigate(R.id.action_homeFragment_to_empDetailsFragment,bundle)
        }

    }

    private fun onEmpItemClick(empData: EmployeeResponse.Datum) {

        selectedEmp.add(empData)

    }

    private fun bindObservers() {
        viewModel.empLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    binding.progressBar.isVisible = false
                    allEmp.addAll(it.data?.data!!)
                    adapter.submitList(it.data?.data)

                }
                is NetworkResult.Error -> Toast.makeText(
                    requireContext(),
                    it.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                is NetworkResult.Loading -> binding.progressBar.isVisible = true

            }
        })

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}