package com.elevenetc.motoalarm.features.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DeviceRegistrationFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_device_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerDevice()
    }

    private fun registerDevice() {

        val progressView = view!!.findViewById<View>(R.id.progress_view)
        val btnRetry = view!!.findViewById<View>(R.id.btn_retry)

        progressView.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE

        subs.add(components.device().registerDevice().run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
            components.navigation().onDeviceRegistered()
        }, {
            it.printStackTrace()
            btnRetry.visibility = View.VISIBLE
            progressView.visibility = View.GONE

            btnRetry.setOnClickListener {
                registerDevice()
            }
        }))
    }

    companion object {
        fun newInstance(): DeviceRegistrationFragment {
            return DeviceRegistrationFragment()
        }
    }
}