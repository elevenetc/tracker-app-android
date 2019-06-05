package com.elevenetc.motoalarm.features.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.elevenetc.motoalarm.core.ui.BaseFragment

class MapFragment : BaseFragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TextView(container!!.context).apply {
            text = "map fragment"
        }
    }

    companion object {
        fun new(): MapFragment {
            return MapFragment()
        }
    }
}