package com.elevenetc.motoalarm.features.device

import com.elevenetc.motoalarm.core.UseCase
import com.elevenetc.motoalarm.core.api.Api
import io.reactivex.Completable
import java.util.*
import javax.inject.Inject

class UpdateMode @Inject constructor(
        private val api: Api
) : UseCase() {
    fun setMode(deviceId: UUID, mode: String): Completable {
        return api.updateDeviceMode(deviceId, mode)
    }
}