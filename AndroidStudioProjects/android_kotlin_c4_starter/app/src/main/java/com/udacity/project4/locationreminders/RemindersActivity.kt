package com.udacity.project4.locationreminders

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.udacity.project4.R
import com.udacity.project4.databinding.ActivityRemindersBinding
import com.udacity.project4.databinding.FragmentSelectLocationBinding

/**
 * The RemindersActivity that holds the reminders fragments
 */



class RemindersActivity : AppCompatActivity() {

//    lateinit var map: GoogleMap

    companion object {

        fun getGoogleMap(map:GoogleMap):GoogleMap {
            return map
        }

    }



    private lateinit var binding: ActivityRemindersBinding

    private lateinit var selectLocationBinding: FragmentSelectLocationBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemindersBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        val selectLocationView = selectLocationBinding.root
//        val currentFragOnDisplay = Navigation.findNavController(selectLocationView).currentDestination?.id
//        if (selectLocationView.id == currentFragOnDisplay) {
////            selectLocationBinding = DataBindingUtil.inflate(layoutInflater, R.id.map, binding.navHostFragment, false)
//            val mapFragment = selectLocationView.findViewById<View>(R.id.map).findFragment<Fragment>() as SupportMapFragment
//            mapFragment.getMapAsync(this)
//        }

//        val mapFrag = supportFragmentManager.findFragmentById(R.id.map)
//        Log.i("ReminderActivity", "R.id.map value is ${mapFrag}")


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        val mapFragment = mapFrag as SupportMapFragment
//        mapFragment.getMapAsync(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {

                binding.navHostFragment.findNavController().popBackStack()
//                (binding.navHostFragment as NavHostFragment).navController.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onMapReady(googleMap: GoogleMap) {
//        map = googleMap
//
//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//    }
}
