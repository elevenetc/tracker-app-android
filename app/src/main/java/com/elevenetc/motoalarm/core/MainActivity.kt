package com.elevenetc.motoalarm.core

import android.os.Bundle
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        components.mainActivity().activity = this
        components.navigation().start()
    }

    override fun onDestroy() {
        components.mainActivity().activity = null
        super.onDestroy()
    }
}