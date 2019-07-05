package com.elevenetc.motoalarm.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SettingsFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.btn_logout).setOnClickListener {


            appComponent.settings().logout().run()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        appComponent.nav().logOut()
                    }, {
                        it.printStackTrace()
                    })
        }
    }

    companion object {
        fun new(): SettingsFragment {
            return SettingsFragment()
        }
    }
}