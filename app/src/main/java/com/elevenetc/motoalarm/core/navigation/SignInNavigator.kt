package com.elevenetc.motoalarm.core.navigation

import com.elevenetc.motoalarm.features.signin.SignInFragment
import javax.inject.Inject

class SignInNavigator @Inject constructor(
        activityKeeper: ActivityKeeper
) : Navigator(activityKeeper) {
    fun toSignIn() {
        replace(SignInFragment.newInstance())
    }
}