package com.elevenetc.motoalarm.core.navigation

import android.support.v7.app.AppCompatActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityKeeper @Inject constructor() {
    var activity: AppCompatActivity? = null

    init {
        System.out.println("created!")
    }
}