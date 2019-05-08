package com.elevenetc.motoalarm.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.features.signin.SignInFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.root_container, SignInFragment.newInstance())
                    .commit()
        }
    }

}
