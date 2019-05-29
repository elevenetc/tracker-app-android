package com.elevenetc.motoalarm.core.ui

import android.support.v7.app.AppCompatActivity
import com.elevenetc.motoalarm.core.app.App
import com.elevenetc.motoalarm.core.app.AppComponent

open class BaseActivity : AppCompatActivity() {
    val appComponent: AppComponent by lazy { (applicationContext as App).appComponent }
}