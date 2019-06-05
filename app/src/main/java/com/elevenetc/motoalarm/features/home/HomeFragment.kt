package com.elevenetc.motoalarm.features.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseFragment
import com.elevenetc.motoalarm.features.devices.DevicesFragment
import com.elevenetc.motoalarm.features.map.MapFragment
import com.elevenetc.motoalarm.features.settings.SettingsFragment

class HomeFragment : BaseFragment() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var contentContainer: ViewGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bottomNavigationView = view.findViewById(R.id.bottom_menu)
        contentContainer = view.findViewById(R.id.home_content_container)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.home_map) {
                childFragmentManager.beginTransaction().replace(R.id.home_content_container, MapFragment.new()).commit()
            } else if (it.itemId == R.id.home_devices) {
                childFragmentManager.beginTransaction().replace(R.id.home_content_container, DevicesFragment.new()).commit()
            } else if (it.itemId == R.id.home_settings) {
                childFragmentManager.beginTransaction().replace(R.id.home_content_container, SettingsFragment.new()).commit()
            }
            true
        }

        bottomNavigationView.selectedItemId = R.id.home_map
    }

    companion object {
        fun instance(): HomeFragment {
            return HomeFragment()
        }
    }

}