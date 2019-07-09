package com.elevenetc.motoalarm.features.device

import dagger.Subcomponent

@Subcomponent
interface DeviceComponent {
    fun registerDevice(): RegisterDeviceUseCase
    fun getDevices(): GetDevicesUseCase
    fun getCurrentDevice(): GetCurrentDevice
}