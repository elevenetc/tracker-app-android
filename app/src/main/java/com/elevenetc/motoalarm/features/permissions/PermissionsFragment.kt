package com.elevenetc.motoalarm.features.permissions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.bus.events.BasePermissionsGranted
import com.elevenetc.motoalarm.core.ui.BaseFragment

class PermissionsFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_permissions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.btn_allow).setOnClickListener {



            subs.add(components.bus().get().filter { it is BasePermissionsGranted }.subscribe {
                components.navigation().onBasePermissionsGranted()
            })

            components.permissions().requestBasePermissions(this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        components.permissions().onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        fun newInstance(): PermissionsFragment {
            return PermissionsFragment()
        }
    }


}