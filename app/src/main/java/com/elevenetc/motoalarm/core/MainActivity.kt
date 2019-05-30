package com.elevenetc.motoalarm.core

import android.os.Bundle
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.mainActivity().activity = this

        if (savedInstanceState == null) {

            appComponent.navigation().root().start()
        }
    }

    override fun onDestroy() {
        appComponent.mainActivity().activity = null
        super.onDestroy()
    }
}