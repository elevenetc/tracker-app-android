package com.elevenetc.motoalarm.features.mode

import android.content.Context
import javax.inject.Inject

class ModeManagerImpl @Inject constructor(
        private val context: Context
) {

    private var mode: Mode = Mode.UNDEFINED
    private var currentMode: ModeImplementation? = null

    fun setMode(mode: Mode) {
        if (mode == this.mode) return

        currentMode?.stop()

        if (mode == Mode.TRACKER) {
            currentMode = TrackerMode(context)
        } else if (mode == Mode.VIEWER) {
            currentMode = ViewerMode()
        }

        this.mode = mode
        currentMode?.start()
    }

}