package com.elevenetc.motoalarm.core.navigation

import com.elevenetc.motoalarm.core.user.UserManagerImpl
import javax.inject.Inject


class RootCoordinator @Inject constructor(
        private val userManager: UserManagerImpl,
        private val homeCoordinator: HomeCoordinator,
        private val signCoordinator: SignInCoordinator
) : Coordinator {

    override fun start() {
        if (userManager.signedIn()) {
            homeCoordinator.start()
        } else {
            signCoordinator.start()
        }
    }
}