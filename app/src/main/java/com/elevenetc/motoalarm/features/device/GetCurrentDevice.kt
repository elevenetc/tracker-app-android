package com.elevenetc.motoalarm.features.device

import com.elevenetc.motoalarm.core.UseCase
import com.elevenetc.motoalarm.core.api.Api
import com.elevenetc.motoalarm.core.cache.KeyValue
import io.reactivex.Single
import javax.inject.Inject

class GetCurrentDevice @Inject constructor(
        val api: Api,
        val keyValue: KeyValue
) : UseCase() {

    fun run(): Single<Device> {
        val deviceId = keyValue.getUUID(KeyValue.Keys.DEVICE_ID)

        return api.getDevices().doOnSuccess { devices ->
            devices.forEach { d -> if (d.id == deviceId) d.current = true }
        }.map { devices ->
            devices.first { device -> device.current }
        }
    }
}