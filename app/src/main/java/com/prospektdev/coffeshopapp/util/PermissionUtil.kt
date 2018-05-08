package com.prospektdev.coffeshopapp.util

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.support.v4.app.ActivityCompat


object PermissionUtil {
    const val REQUEST_CODE_PERMISSIONS_LOCATION = 1001

    fun isLocationEnabled(context: Context): Boolean {
        val lm: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun needPermissions(context: Context): Boolean {
        val permissions = getPermissionsArray()
        permissions.forEach {
            if (isNotGranted(context, it)) return true
        }
        return false
    }

    fun requestPermissions(activity: Activity) {
        val permissions = getPermissionsArray()
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE_PERMISSIONS_LOCATION)
    }

    private fun isNotGranted(context: Context, item: String) =
            ActivityCompat.checkSelfPermission(context, item) != PackageManager.PERMISSION_GRANTED

    private fun getPermissionsArray() = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
}