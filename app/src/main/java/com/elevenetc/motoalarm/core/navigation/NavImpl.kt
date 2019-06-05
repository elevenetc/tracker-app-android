package com.elevenetc.motoalarm.core.navigation

import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.features.signin.SignInFragment
import javax.inject.Inject

class NavImpl @Inject constructor(
        private val activityKeeper: ActivityKeeper
) : Nav {
    override fun signOut() {
        activityKeeper.activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_container, SignInFragment.newInstance())
                .commit()
    }
}