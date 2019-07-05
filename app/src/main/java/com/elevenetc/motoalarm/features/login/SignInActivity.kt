package com.elevenetc.motoalarm.features.login

import android.os.Bundle
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseActivity

class SignInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.mainActivity().activity = this

        if (savedInstanceState == null) {
            appComponent.navigation().root().start()
        }
    }


}