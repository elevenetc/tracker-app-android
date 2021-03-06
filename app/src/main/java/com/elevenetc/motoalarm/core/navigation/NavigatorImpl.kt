package com.elevenetc.motoalarm.core.navigation

import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.permissions.PermissionsManagerImpl
import com.elevenetc.motoalarm.core.ui.BaseFragment
import com.elevenetc.motoalarm.features.device.DeviceActivity
import com.elevenetc.motoalarm.features.device.DeviceRegistrationFragment
import com.elevenetc.motoalarm.features.home.HomeFragment
import com.elevenetc.motoalarm.features.login.LogInFragment
import com.elevenetc.motoalarm.features.permissions.PermissionsFragment
import java.util.*
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
        val activityKeeper: ActivityKeeper,
        val keyValue: KeyValue,
        val permissions: PermissionsManagerImpl
) : Navigator {

    override fun start() {
        if (keyValue.getBool(KeyValue.Keys.LOGGED_IN)) {
            onLoggedIn()
        } else {
            goToLogin()
        }
    }

    override fun onLoggedIn() {
        if (keyValue.getBool(KeyValue.Keys.DEVICE_REGISTERED)) {
            goToHome()
        } else {
            goToDeviceRegistration()
        }
    }

    override fun openDevice(deviceId: UUID) {
        DeviceActivity.start(deviceId, activityKeeper.activity!!)
    }

    override fun onDeviceRegistered() {

        if (permissions.basePermissionsGranted()) {
            goToHome()
        } else {
            goToBasePermissions()
        }
    }

    override fun goToLogin() {
        addIfNotAdded(LogInFragment.newInstance(), TAG_LOGIN)
    }

    override fun goToDeviceRegistration() {
        addIfNotAdded(DeviceRegistrationFragment.newInstance(), TAG_DEVICE_REGISTRATION)
    }

    override fun goToHome() {
        addIfNotAdded(HomeFragment.newInstance(), TAG_HOME)
    }

    private fun goToBasePermissions() {
        addIfNotAdded(PermissionsFragment.newInstance(), PERMISSIONS)
    }

    override fun onBasePermissionsGranted() {
        goToHome()
    }

    private fun addIfNotAdded(fragment: BaseFragment, tag: String) {

        val fm = activityKeeper.activity!!.supportFragmentManager
        val fmFragment = fm.findFragmentByTag(tag)

        if (fmFragment == null) {
            fm.beginTransaction()
                    .replace(R.id.root_container, fragment, tag)
                    .commit()
        }
    }

    private companion object {
        const val TAG_LOGIN = "login"
        const val TAG_HOME = "home"
        const val TAG_DEVICE_REGISTRATION = "device-registration"
        const val PERMISSIONS = "permissions"
    }
}