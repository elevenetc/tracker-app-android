package com.elevenetc.motoalarm.features.settings

import dagger.Subcomponent

@Subcomponent
interface SettingsComponent {
    fun signOut(): SignOut
}