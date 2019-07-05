package com.elevenetc.motoalarm.features.login

import dagger.Subcomponent

@Subcomponent
interface LogInComponent {
    fun viewModel(): LogInViewModel
}