package com.elevenetc.motoalarm.core.app

import android.content.Context
import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.cache.KeyValueImpl
import dagger.Module
import dagger.Provides

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
}