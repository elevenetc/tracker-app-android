package com.elevenetc.motoalarm.core.user

import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [UserComponent.UserModule::class])
interface UserComponent {

    @dagger.Module
    class UserModule {
        @Provides
        fun userManager(inst: UserManagerImpl): UserManager = inst
    }
}

