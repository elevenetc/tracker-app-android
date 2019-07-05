package com.elevenetc.motoalarm.core.api

import com.elevenetc.motoalarm.core.user.User
import com.elevenetc.motoalarm.features.device.Device
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface Api {
    fun login(email: String, password: String): Single<Pair<AccessToken, User>>

    fun register(email: String, password: String): Single<Pair<AccessToken, User>>

    fun registerDevice(hardwareId: String,
                       manufacturer: String,
                       name: String,
                       accessToken: AccessToken,
                       userId: UUID): Single<Device>

    fun logout(accessToken: AccessToken,
               userId: UUID): Completable
}