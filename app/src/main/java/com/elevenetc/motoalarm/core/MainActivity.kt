package com.elevenetc.motoalarm.core

import android.os.Bundle
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseActivity
import com.elevenetc.motoalarm.features.signin.SignInFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        if (savedInstanceState == null) {

            appComponent.navigation().root().start()

            supportFragmentManager.beginTransaction()
                    .add(R.id.root_container, SignInFragment.newInstance())
                    .commit()
        }
    }

}
