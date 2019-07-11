package com.elevenetc.motoalarm.features.device

import com.elevenetc.motoalarm.core.UseCase
import com.elevenetc.motoalarm.core.api.Api
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class GetDevice @Inject constructor(
        private val api: Api
): UseCase() {
    fun run(deviceId: UUID): Single<Device> {
        return api.getDevice(deviceId)
    }
}