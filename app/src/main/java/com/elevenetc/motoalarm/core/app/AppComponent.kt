package com.elevenetc.motoalarm.core.app

import android.content.Context
import com.elevenetc.motoalarm.core.navigation.ActivityKeeper
import com.elevenetc.motoalarm.core.navigation.Nav
import com.elevenetc.motoalarm.core.navigation.NavigationComponent
import com.elevenetc.motoalarm.features.settings.SettingsComponent
import com.elevenetc.motoalarm.features.signin.SingInComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun mainActivity(): ActivityKeeper
    fun context(): Context

    fun navigation(): NavigationComponent
    fun signIn(): SingInComponent
    fun settings(): SettingsComponent

    fun nav(): Nav
}