package com.elevenetc.motoalarm.features.mode

import android.content.Context
import com.elevenetc.motoalarm.core.api.Api
import com.elevenetc.motoalarm.core.bus.BusImpl
import com.elevenetc.motoalarm.core.cache.KeyValue
import javax.inject.Inject

class ModeManagerImpl @Inject constructor(
        private val context: Context,
        private val bus:BusImpl,
        private val api:Api,
        private val keyValue: KeyValue
) {

    private var mode: Mode = Mode.UNDEFINED
    private var currentMode: ModeImplementation? = null

    fun setMode(mode: Mode) {
        if (mode == this.mode) return

        currentMode?.stop()

        if (mode == Mode.TRACKER) {
            currentMode = TrackerMode(context, bus, api, keyValue)
        } else if (mode == Mode.VIEWER) {
            currentMode = ViewerMode()
        }

        this.mode = mode
        currentMode?.start()
    }

}