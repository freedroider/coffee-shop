package com.prospektdev.coffeshopapp.core.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.prospektdev.coffeshopapp.R
import com.prospektdev.coffeshopapp.extention.action
import com.prospektdev.coffeshopapp.extention.snack
import com.prospektdev.coffeshopapp.util.Logger
import com.prospektdev.coffeshopapp.util.PermissionUtil
import com.prospektdev.coffeshopapp.util.Starter
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        super.setContentView(obtainLayoutId())
        Logger.e("create")
    }

    override fun onResume() {
        super.onResume()
        Logger.e("resume")
        checkPermissionsAndGps()
    }

    override fun setContentView(layoutResID: Int): Nothing =
            throw UnsupportedOperationException("Use obtainLayoutId()")

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when {PermissionUtil.needPermissions(this) ->
            requestPermissionsWithRationale()
            else -> checkLocationEnabled()
        }
    }

    protected fun <VM : ViewModel> provideViewModel(viewModelClass: Class<VM>) =
            ViewModelProviders.of(this, viewModelFactory)[viewModelClass]

    private fun checkPermissionsAndGps() {
        when {
            PermissionUtil.needPermissions(this)
            -> PermissionUtil.requestPermissions(this)
            else -> checkLocationEnabled()
        }
    }

    private fun checkLocationEnabled() {
        when {
            PermissionUtil.isLocationEnabled(this) && isOnline() -> onLocationEnabled()
            !PermissionUtil.isLocationEnabled(this) -> enableLocation()
            !isOnline() -> snack(R.string.no_internet_connection)
        }
    }

    private fun requestPermissionsWithRationale() {
        snackWithAction(
                R.string.location_rationale,
                R.string.enable,
                { PermissionUtil.requestPermissions(this@BaseActivity) }
        )
    }

    private fun enableLocation() {
        snackWithAction(
                R.string.gps_rationale,
                R.string.enable,
                { Starter.startLocationSettings(this@BaseActivity) }
        )
    }

    private fun snackWithAction(@StringRes msg: Int, @StringRes action: Int, listener: (View) -> Unit) {
        val view = findViewById<View>(android.R.id.content)
        view.snack(msg) { action(action) { listener.invoke(it) } }
    }

    private fun snack(@StringRes message: Int) {
        val view = findViewById<View>(android.R.id.content)
        view.snack(message) {}
    }

    private fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    abstract fun onLocationEnabled()

    @LayoutRes
    abstract fun obtainLayoutId(): Int
}