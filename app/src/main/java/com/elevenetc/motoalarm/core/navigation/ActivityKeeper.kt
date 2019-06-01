package com.elevenetc.motoalarm.core.navigation

import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

class ActivityKeeper @Inject constructor() {
    var activity: AppCompatActivity? = null

    init {
        System.out.println("created!")
    }
}