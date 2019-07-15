package com.elevenetc.motoalarm.core.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.elevenetc.motoalarm.core.bus.BusImpl
import com.elevenetc.motoalarm.core.bus.events.BasePermissionsGranted
import com.elevenetc.motoalarm.core.navigation.ActivityKeeper
import com.elevenetc.motoalarm.core.ui.BaseFragment
import javax.inject.Inject

class PermissionsManagerImpl @Inject constructor(
        private val context: Context,
        private val activityKeeper: ActivityKeeper,
        private val bus: BusImpl
) {
    fun basePermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    fun requestBasePermissions(fragment: BaseFragment) {
        fragment.requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 6666)
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 6666 && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION && grantResults[0] == 0) {
            bus.post(BasePermissionsGranted())
        }
    }

    private fun requestBasePermissions() {
        val activity = activityKeeper.activity!!
        val shouldRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (shouldRequestPermission) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    6666)

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }
}