package com.elevenetc.motoalarm.core.navigation

import javax.inject.Inject

class HomeCoordinator @Inject constructor(
        private val homeNavigator: HomeNavigator
) : Coordinator {
    override fun start() {
        homeNavigator.startHome()
    }
}