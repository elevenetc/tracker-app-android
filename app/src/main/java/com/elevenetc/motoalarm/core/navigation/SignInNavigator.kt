package com.elevenetc.motoalarm.core.navigation

import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.features.signin.SignInFragment
import javax.inject.Inject

class SignInNavigator @Inject constructor(
        private val mainActivityKeeper: MainActivityKeeper
) {
    fun toSignIn() {
        mainActivityKeeper.activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.root_container, SignInFragment.newInstance())
                .commit()
    }
}