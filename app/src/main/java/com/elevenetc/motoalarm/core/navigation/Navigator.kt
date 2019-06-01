package com.elevenetc.motoalarm.core.navigation

import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseFragment

open class Navigator constructor(
        private val activityKeeper: ActivityKeeper
) {
    fun replace(fragment: BaseFragment) {
        activityKeeper.activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.root_container, fragment)
                .commit()
    }
}