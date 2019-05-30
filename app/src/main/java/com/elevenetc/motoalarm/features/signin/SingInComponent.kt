package com.elevenetc.motoalarm.features.signin

import dagger.Subcomponent

@Subcomponent
interface SingInComponent {
    fun viewModel(): SignInViewModel
}