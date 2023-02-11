package idroid.android.samplelocationkit

import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import idroid.android.locationkit.listener.LocationListener
import idroid.android.locationkit.manager.HuaweiGoogleLocationManager

class MainActivity : AppCompatActivity() {
    lateinit var locationService: HuaweiGoogleLocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationService = HuaweiGoogleLocationManager(this)

        if (!locationService.isLocationPermissionGranted()) locationService.requestLocationPermission()

        findViewById<Button>(R.id.btnCurrentLocation).setOnClickListener {
            locationService.requestLocationUpdates(object : LocationListener {
                override fun onLocationUpdate(currentLocation: Location) {
                    Toast.makeText(
                        this@MainActivity,
                        "Current Location Latitude: ${currentLocation.latitude} ,Current Location Longitude: ${currentLocation.longitude}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }

        findViewById<Button>(R.id.btnLastKnownLocation).setOnClickListener {
            locationService.getLastKnownLocation(object : LocationListener {
                override fun onLocationUpdate(lastKnownLocation: Location) {
                    Toast.makeText(
                        this@MainActivity,
                        "Last Known Location Latitude: ${lastKnownLocation.latitude} ,Current Location Longitude: ${lastKnownLocation.longitude}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }

        findViewById<Button>(R.id.btnStopCurrentLocation).setOnClickListener {
            locationService.removeLocationUpdates()
        }
    }

    override fun onDestroy() {
        locationService.removeLocationUpdates()
        super.onDestroy()
    }
}
