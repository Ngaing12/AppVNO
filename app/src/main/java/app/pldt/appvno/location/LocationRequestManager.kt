package app.pldt.appvno.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.*
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.pldt.appvno.ui.models.LocationDetails
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.*

object LocationRequestManager {


    private val location = MutableLiveData<LocationDetails>()
    fun getCurrentLocationDetail(): LiveData<LocationDetails> =
        location
    fun setLocation(locationDetails: LocationDetails){
        location.value = locationDetails
    }

    private lateinit var  locationManager : LocationManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit  var mContext: Context

    operator fun invoke(context: Context) {
        mContext = context
        locationManager =  mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    fun isServicesOK() : Boolean{
        return when (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
            mContext
        )) {
            ConnectionResult.SUCCESS -> {
                //everything is fine and the user can make map requests
                true
            }
            else -> false
        }
    }

    fun  checkGPS() : Boolean{
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun getCurrentLocationViaGoogle() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location != null) {
                    getLocationDetail(
                        location.latitude,
                        location.longitude
                    )
                } else {
                    getCurrentLocationViaLocal()
                }
            }
            .addOnFailureListener {
            }
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocationViaLocal(){

        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_FINE
        locationManager.requestSingleUpdate(criteria, object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                location?.let {
                    getLocationDetail(
                        it.latitude,
                        it.longitude
                    )
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderDisabled(provider: String?) {}
            override fun onProviderEnabled(provider: String?) {}
        }, null )
    }


    fun getLocationDetail(lat : Double, lon: Double ){
        val geoCoder = Geocoder(mContext, Locale.getDefault())
        val addresses: MutableList<Address>

        try {
            addresses = geoCoder.getFromLocation(lat, lon, 10)
            if (addresses.count() > 0) {
                val locationDetails = LocationDetails(
                    countryCode =  addresses[0].countryCode ?: "",
                    countryName =  addresses[0].countryName ?: "",
                    state = addresses[0].adminArea ?: "",
                    city  = addresses[0].locality ?: "",
                    street = addresses[0].thoroughfare ?: "",
                    streetNo = addresses[0].subThoroughfare ?: "",
                    postalCode = addresses[0].postalCode ?: ""
                )
                setLocation(
                    locationDetails
                )
            }
        } catch (e : IOException) {
            Log.d("Something", e.toString())
        }
    }
}