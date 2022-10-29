package com.mep.it.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mep.it.databinding.FragmentZigzagBinding
import com.mep.it.ui.adapters.ZigZagAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ZigZagFragment : Fragment() {

    private lateinit var binding: FragmentZigzagBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentZigzagBinding.inflate(inflater, container, false)
        (activity as HomeActivity).binding.toolbar.title = "Zig Zag"
        (activity as HomeActivity).binding.bottomNavView.visibility = View.VISIBLE
        (activity as HomeActivity).binding.btnNext.visibility = View.GONE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ZigZagAdapter()
        binding.rvGrid.apply {
            // setting grid layout manager to implement grid view.
            // in this method '2' represents number of columns to be displayed in grid view.
            val layoutManager = GridLayoutManager(context, 2)
            // at last set adapter to recycler view.
            this.layoutManager = layoutManager
            this.adapter = adapter

        }


    }


}