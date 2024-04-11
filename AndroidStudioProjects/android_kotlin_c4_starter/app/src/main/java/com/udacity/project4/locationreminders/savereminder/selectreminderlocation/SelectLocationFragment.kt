package com.udacity.project4.locationreminders.savereminder.selectreminderlocation

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.udacity.project4.R
import com.udacity.project4.base.BaseFragment
import com.udacity.project4.databinding.FragmentSelectLocationBinding
import com.udacity.project4.locationreminders.RemindersActivity
import com.udacity.project4.locationreminders.savereminder.SaveReminderViewModel
import com.udacity.project4.utils.setDisplayHomeAsUpEnabled
import org.koin.android.ext.android.inject

class SelectLocationFragment : BaseFragment(), OnMapReadyCallback {


    private lateinit var map: GoogleMap

    // Use Koin to get the view model of the SaveReminder
    override val _viewModel: SaveReminderViewModel by inject()
    private lateinit var binding: FragmentSelectLocationBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val layoutId = R.layout.fragment_select_location
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)


        binding.viewModel = _viewModel
        binding.lifecycleOwner = this



        
        val mapFragXXX = childFragmentManager.findFragmentById(R.id.map)
        Log.i("SelectLocationFragment", "The value of mapFragXXX is $mapFragXXX")
        val mapFragment = mapFragXXX as SupportMapFragment
        mapFragment.getMapAsync(this)

//        val mapFragXX = this.activity?.supportFragmentManager?.findFragmentById(R.id.map)
//        Log.i("SelectLocationFragment", "The value of mapFragXX is $mapFragXX")
//        val mapFragment = mapFragXX as SupportMapFragment
//        mapFragment.getMapAsync(this)
//
//        val mapFragX = parentFragmentManager.findFragmentById(R.id.map)
////        This is returned null for mapFragX because this fragment is not associated with a transaction or host
//        Log.i("SelectLocationFragment", "The value of mapFragX is $mapFragX")

//        val mapFrag = (parentFragment as NavHostFragment).childFragmentManager.findFragmentById(R.id.map)
//        This is returning null for mapFrag because this fragment (SelectLocationFragment) is attached directly to the
//        RemindersActivity class (it is not attached/associated with NavHostFragment)

//        val mapFrag = parentFragmentManager.findFragmentById()
//        Log.i("SelectLocationFragment", "The value of mapFrag is $mapFrag")
//        val mapFragment = mapFrag as SupportMapFragment
//        mapFragment.getMapAsync(this)


//        val mapFragY = (parentFragment as NavHostFragment).parentFragmentManager.findFragmentById(R.id.map)
//        Log.i("SelectLocationFragment", "The value of mapFragY is $mapFragY")


        setHasOptionsMenu(true)
        setDisplayHomeAsUpEnabled(true)

        // TODO: add the map setup implementation
        // TODO: zoom to the user location after taking his permission
        // TODO: add style to the map
        // TODO: put a marker to location that the user selected

        // TODO: call this function after the user confirms on the selected location
        onLocationSelected()
        return binding.root
    }

    private fun onLocationSelected() {
        // TODO: When the user confirms on the selected location,
        //  send back the selected location details to the view model
        //  and navigate back to the previous fragment to save the reminder and add the geofence
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.map_options, menu)


    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        // TODO: Change the map type based on the user's selection.
        R.id.normal_map -> {
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            true
        }
        R.id.hybrid_map -> {
            map.mapType = GoogleMap.MAP_TYPE_HYBRID
            true
        }
        R.id.satellite_map -> {
            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            true
        }
        R.id.terrain_map -> {
            map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap

        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))

    }

}