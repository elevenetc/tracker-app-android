package com.elevenetc.motoalarm.core.user

import dagger.Subcomponent

@Subcomponent
interface UserComponent {
    fun usermanager(): UserManagerImpl
}