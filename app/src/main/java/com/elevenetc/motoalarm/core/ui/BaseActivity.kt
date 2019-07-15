package com.elevenetc.motoalarm.core.ui

import android.support.v7.app.AppCompatActivity
import com.elevenetc.motoalarm.core.app.App
import com.elevenetc.motoalarm.core.app.AppComponent

open class BaseActivity : AppCompatActivity() {
    val components: AppComponent by lazy { (applicationContext as App).appComponent }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}