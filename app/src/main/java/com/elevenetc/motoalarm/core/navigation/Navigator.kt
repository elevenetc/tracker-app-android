package com.elevenetc.motoalarm.core.navigation

interface Navigator {
    fun start()
    fun goToDeviceRegistration()
    fun goToHome()
    fun goToLogin()
    fun onLoggedIn()
    fun onDeviceRegistered()
    fun openDevice()
}