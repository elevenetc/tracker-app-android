package com.elevenetc.motoalarm.features.login

import com.elevenetc.motoalarm.core.UseCase
import com.elevenetc.motoalarm.core.api.Api
import com.elevenetc.motoalarm.core.cache.KeyValue
import io.reactivex.Completable

class LoginUseCase(
        private val api: Api,
        private val keyValue: KeyValue
) : UseCase() {
    fun run(email: String, password: String): Completable {

        return api.login(email, password).flatMapCompletable {
            val token = it.first
            val user = it.second

            keyValue.setString(KeyValue.Keys.ACCESS_TOKEN, token.value)
            keyValue.setString(KeyValue.Keys.USER_ID, user.id)
            keyValue.setBool(KeyValue.Keys.LOGGED_IN, true)

            Completable.complete()
        }
    }
}