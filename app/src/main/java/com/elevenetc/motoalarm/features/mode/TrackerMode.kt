package com.elevenetc.motoalarm.features.mode

import android.content.Context
import android.content.Intent
import com.elevenetc.motoalarm.core.api.Api
import com.elevenetc.motoalarm.core.bus.BusImpl
import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.location.Loc
import com.elevenetc.motoalarm.core.location.LocationService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrackerMode @Inject constructor(
        private val context: Context,
        private val bus: BusImpl,
        private val api: Api,
        private val keyValue: KeyValue
) : ModeImplementation {

    private var locSub: Disposable? = null

    override fun start() {
        locSub = bus.get()
                .filter { it is Loc }
                .cast(Loc::class.java)
                .subscribe { sendLoc(it) }
        context.startService(Intent(context, LocationService::class.java))
    }

    override fun stop() {
        locSub?.dispose()
        context.stopService(Intent(context, LocationService::class.java))
    }

    private fun sendLoc(loc: Loc) {


        val currentDeviceId = keyValue.getUUID(KeyValue.Keys.DEVICE_ID)
        api.postState(loc, 1.0f, currentDeviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {
                    it.printStackTrace()
                })
    }
}