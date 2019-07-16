package com.elevenetc.motoalarm.core.app

import android.content.Context
import com.elevenetc.motoalarm.core.bus.BusImpl
import com.elevenetc.motoalarm.core.navigation.ActivityKeeper
import com.elevenetc.motoalarm.core.navigation.Navigator
import com.elevenetc.motoalarm.core.permissions.PermissionsManagerImpl
import com.elevenetc.motoalarm.features.device.DeviceComponent
import com.elevenetc.motoalarm.features.login.LogInComponent
import com.elevenetc.motoalarm.features.mode.ModeManagerImpl
import com.elevenetc.motoalarm.features.settings.SettingsComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun mainActivity(): ActivityKeeper
    fun context(): Context

    fun signIn(): LogInComponent
    fun settings(): SettingsComponent
    fun device(): DeviceComponent

    fun navigation(): Navigator

    fun permissions(): PermissionsManagerImpl
    fun bus(): BusImpl

    fun mode(): ModeManagerImpl
}