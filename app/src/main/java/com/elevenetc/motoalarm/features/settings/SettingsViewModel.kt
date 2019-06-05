package com.elevenetc.motoalarm.features.settings

import com.elevenetc.motoalarm.core.utils.RxUtils
import io.reactivex.Completable
import javax.inject.Inject

class SettingsViewModel @Inject constructor(val signOut: SignOut) {
    fun signOut(): Completable {
        return signOut().compose(RxUtils.scheduler())
    }
}