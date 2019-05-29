package com.elevenetc.motoalarm.core.navigation

import dagger.Subcomponent

@Subcomponent
interface NavigationComponent {
    fun root(): RootCoordinator
}