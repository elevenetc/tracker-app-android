package com.elevenetc.motoalarm.features.settings

import com.elevenetc.motoalarm.core.api.Api
import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.utils.RxUtils
import com.elevenetc.motoalarm.features.login.LogOutUseCase
import io.reactivex.Completable
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
        private val api: Api,
        private val keyValue: KeyValue
) {
    fun logOut(): Completable {
        return LogOutUseCase(api, keyValue).run().compose(RxUtils.scheduler())
    }
}