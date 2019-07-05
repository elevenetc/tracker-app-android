package com.elevenetc.motoalarm.core.navigation

import com.elevenetc.motoalarm.core.cache.KeyValue
import javax.inject.Inject


class RootCoordinator @Inject constructor(
        private val keyValue: KeyValue,
        private val homeCoordinator: HomeCoordinator,
        private val signCoordinator: SignInCoordinator
) : Coordinator {

    override fun start() {
        if (keyValue.getBool(KeyValue.Keys.LOGGED_IN)) {
            homeCoordinator.start()
        } else {
            signCoordinator.start()
        }
    }
}