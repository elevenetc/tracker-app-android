package com.elevenetc.motoalarm.features.login

import com.elevenetc.motoalarm.core.UseCase
import com.elevenetc.motoalarm.core.api.AccessToken
import com.elevenetc.motoalarm.core.api.Api
import com.elevenetc.motoalarm.core.cache.KeyValue
import io.reactivex.Completable
import io.reactivex.Completable.fromAction
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
        private val api: Api,
        private val keyValue: KeyValue) : UseCase() {
    fun run(): Completable {

        val token = keyValue.getUUID(KeyValue.Keys.ACCESS_TOKEN)
        val userId = keyValue.getUUID(KeyValue.Keys.USER_ID)

        return api.logout(AccessToken(token), userId).andThen(fromAction {
            keyValue.clear()
        })
    }
}