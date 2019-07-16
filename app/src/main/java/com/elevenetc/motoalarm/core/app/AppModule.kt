package com.elevenetc.motoalarm.core.app

import android.content.Context
import com.elevenetc.motoalarm.core.api.Api
import com.elevenetc.motoalarm.core.api.ApiImpl
import com.elevenetc.motoalarm.core.bus.BusImpl
import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.cache.KeyValueImpl
import com.elevenetc.motoalarm.core.navigation.ActivityKeeper
import com.elevenetc.motoalarm.core.navigation.Navigator
import com.elevenetc.motoalarm.core.navigation.NavigatorImpl
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
    @Singleton
    fun api(inst: ApiImpl): Api = inst

    @Provides
    fun nav(inst: NavigatorImpl): Navigator = inst

    @Provides
    @Singleton
    fun bus(): BusImpl {
        return BusImpl()
    }
}