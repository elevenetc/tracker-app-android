package com.elevenetc.motoalarm.core.navigation

import javax.inject.Inject

class SignInCoordinator @Inject constructor(
        private val signInNavigator: SignInNavigator
) : Coordinator {
    override fun start() {
        signInNavigator.toSignIn()
    }
}