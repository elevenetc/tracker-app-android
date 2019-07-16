package com.elevenetc.motoalarm.features.mode

import android.content.Context
import android.content.Intent
import com.elevenetc.motoalarm.core.location.LocationService
import javax.inject.Inject

class TrackerMode @Inject constructor(
        private val context: Context
) : ModeImplementation {
    override fun start() {
        context.startService(Intent(context, LocationService::class.java))
    }

    override fun stop() {
        context.stopService(Intent(context, LocationService::class.java))
    }
}