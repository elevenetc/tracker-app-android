package com.elevenetc.motoalarm.features.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.elevenetc.motoalarm.core.ui.BaseFragment

class DevicesFragment : BaseFragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TextView(container!!.context).apply {
            text = "devices fragment"
        }
    }

    companion object {
        fun new(): DevicesFragment {
            return DevicesFragment()
        }
    }
}