package com.mep.it.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.mep.it.R
import com.mep.it.databinding.FragmentMapBinding
import com.mep.it.model.EmployeeResponse
import com.mep.it.util.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var mMap: GoogleMap

    private var empData: ArrayList<EmployeeResponse.Datum>? = null
    private var mMarkerArray = ArrayList<Marker>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        (activity as HomeActivity).binding.toolbar.title = "Employee List"
        (activity as HomeActivity).binding.bottomNavView.visibility = View.VISIBLE
        (activity as HomeActivity).binding.btnNext.visibility = View.GONE
        (activity as HomeActivity).binding.bottomNavView.visibility = View.GONE

        // in below line we are initializing our array list.
        empData = ArrayList()
        mMarkerArray = ArrayList()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()
        mapSetup()
        bindHandlers()
    }


    private fun setInitialData() {
         empData = arguments?.getSerializable("empList") as ArrayList<EmployeeResponse.Datum>
        empData.let {
          //  adapter.submitList(empData)
            binding.empData = it!![0]

        }
    }

    private fun mapSetup() {

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun bindHandlers() {

        binding.floatingButton.setOnClickListener {
            findNavController().popBackStack()
        }
        
    }


    override fun onMapReady(map: GoogleMap) {
        // inside on map ready method
        // we will be displaying all our markers.
        // for adding markers we are running for loop and
        // inside that we are drawing marker on our map.
        mMap = map
        mMap.setOnMarkerClickListener { marker ->
            val data = marker.tag as EmployeeResponse.Datum?
            binding.empData = data
            false
        }
         if (empData!!.isNotEmpty()) {
                for (i in empData!!.indices) {
                    // below line is use to add marker to each location of our array list.
                    if (empData!![i].technology==Constants.ANDROID){
                        val greenPin = BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_green)

                        val marker = mMap.addMarker(MarkerOptions().position(LatLng(empData!![i].lat!!.toDouble(), empData!![i].lng!!.toDouble()))
                            .icon(greenPin))
                        marker!!.tag = empData!![i]

                        mMarkerArray.add(marker!!)
                    }
                    else {
                        val blackPin = BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_black)

                        val marker  = mMap.addMarker(MarkerOptions().position(LatLng(empData!![i].lat!!.toDouble(), empData!![i].lng!!.toDouble()))
                            .icon(blackPin))
                        marker!!.tag = empData!![i]

                        mMarkerArray.add(marker!!)

                    }

                    val builder = LatLngBounds.Builder()

                    for (marker in mMarkerArray) {
                        builder.include(marker.position)
                    }
                    val bounds = builder.build()
                    val padding = 0 // offset from edges of the map in pixels

                    val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)

                    // below lin is use to zoom our camera on map.
                    mMap.moveCamera(cu)
                    binding.progressBar.visibility = View.GONE

                }
            }




    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}