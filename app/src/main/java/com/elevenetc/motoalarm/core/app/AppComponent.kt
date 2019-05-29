package com.elevenetc.motoalarm.core.app

import android.content.Context
import com.elevenetc.motoalarm.core.navigation.NavigationComponent
import com.elevenetc.motoalarm.core.user.UserComponent
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun context(): Context

    fun navigation(): NavigationComponent
    fun user(): UserComponent
}