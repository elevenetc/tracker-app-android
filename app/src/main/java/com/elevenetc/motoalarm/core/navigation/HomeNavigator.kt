package com.elevenetc.motoalarm.core.navigation

import com.elevenetc.motoalarm.features.home.HomeFragment
import javax.inject.Inject

class HomeNavigator
@Inject constructor(activityKeeper: ActivityKeeper) :
        Navigator(activityKeeper) {
    fun startHome() {
        replace(HomeFragment.instance())
    }
}