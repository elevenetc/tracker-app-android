package com.elevenetc.motoalarm.features.device

import com.elevenetc.motoalarm.core.UseCase
import com.elevenetc.motoalarm.core.api.AccessToken
import com.elevenetc.motoalarm.core.api.Api
import com.elevenetc.motoalarm.core.cache.KeyValue
import io.reactivex.Single
import javax.inject.Inject

class GetDevicesUseCase @Inject constructor(
        val api: Api,
        val keyValue: KeyValue
) : UseCase() {
    fun run(): Single<List<Device>> {

        val accessToken = keyValue.getUUID(KeyValue.Keys.ACCESS_TOKEN)
        val userId = keyValue.getUUID(KeyValue.Keys.USER_ID)
        val deviceId = keyValue.getUUID(KeyValue.Keys.DEVICE_ID)

        return api.getDevices(AccessToken(accessToken), userId).doOnSuccess {
            it.forEach { d -> if (d.id == deviceId) d.current = true }
        }
    }
}