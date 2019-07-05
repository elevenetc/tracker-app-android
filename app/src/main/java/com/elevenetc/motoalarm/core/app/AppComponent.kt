package com.elevenetc.motoalarm.core.app

import android.content.Context
import com.elevenetc.motoalarm.core.navigation.ActivityKeeper
import com.elevenetc.motoalarm.core.navigation.Navigator
import com.elevenetc.motoalarm.features.login.LogInComponent
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

    fun navigation(): Navigator
}