package com.elevenetc.motoalarm.core.app

import android.content.Context
import com.elevenetc.motoalarm.core.navigation.MainActivityKeeper
import com.elevenetc.motoalarm.core.navigation.NavigationComponent
import com.elevenetc.motoalarm.core.user.UserComponent
import com.elevenetc.motoalarm.core.user.UserManager
import com.elevenetc.motoalarm.features.signin.SingInComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun mainActivity(): MainActivityKeeper
    fun context(): Context
    fun userManager(): UserManager

    fun navigation(): NavigationComponent
    fun signIn(): SingInComponent
    fun user(): UserComponent
}