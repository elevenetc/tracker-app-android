package com.elevenetc.motoalarm.core.navigation

import java.util.*

interface Navigator {
    fun start()
    fun goToDeviceRegistration()
    fun goToHome()
    fun goToLogin()
    fun onLoggedIn()
    fun onDeviceRegistered()
    fun openDevice(deviceId: UUID)
    fun onBasePermissionsGranted()
}