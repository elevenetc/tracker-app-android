package com.elevenetc.motoalarm.features.settings

import com.elevenetc.motoalarm.features.login.LogOutUseCase
import dagger.Subcomponent

@Subcomponent
interface SettingsComponent {
    fun logout():LogOutUseCase
}