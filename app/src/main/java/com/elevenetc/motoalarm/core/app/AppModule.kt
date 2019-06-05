package com.elevenetc.motoalarm.core.app

import android.content.Context
import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.cache.KeyValueImpl
import com.elevenetc.motoalarm.core.navigation.ActivityKeeper
import com.elevenetc.motoalarm.core.navigation.Nav
import com.elevenetc.motoalarm.core.navigation.NavImpl
import com.elevenetc.motoalarm.core.user.UserManager
import com.elevenetc.motoalarm.core.user.UserManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    fun appContext(): Context {
        return context
    }

    @Provides
    fun keyValue(): KeyValue {
        return KeyValueImpl(context)
    }

    @Provides
    @Singleton
    fun mainActivity(): ActivityKeeper {
        return ActivityKeeper()
    }

    @Provides
    fun userManager(inst: UserManagerImpl): UserManager = inst

    @Provides
    fun nav(inst: NavImpl): Nav = inst
}