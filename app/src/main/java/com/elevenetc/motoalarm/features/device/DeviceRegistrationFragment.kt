package com.elevenetc.motoalarm.features.device

import android.os.Bundle
import android.view.View
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseFragment

class DeviceRegistrationFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerDevice()
    }

    private fun registerDevice() {

        val progressView = view!!.findViewById<View>(R.id.progress_view)
        val btnRetry = view!!.findViewById<View>(R.id.btn_retry)

        progressView.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE

        subs.add(appComponent.device().registerDevice().run().subscribe({
            appComponent.navigation().goToHome()
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