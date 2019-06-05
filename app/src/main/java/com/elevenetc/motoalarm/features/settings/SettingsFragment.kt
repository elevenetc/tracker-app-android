package com.elevenetc.motoalarm.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseFragment

class SettingsFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.btn_logout).setOnClickListener {
            appComponent.settings().signOut().run().subscribe({
                appComponent.nav().signOut()
            }, {

            })
        }
    }

    companion object {
        fun new(): SettingsFragment {
            return SettingsFragment()
        }
    }
}