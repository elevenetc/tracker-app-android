package com.elevenetc.motoalarm.features.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DeviceFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textName = view.findViewById<TextView>(R.id.text_name)
        val textManufacturer = view.findViewById<TextView>(R.id.text_manufacturer)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
        val btnTracker = view.findViewById<RadioButton>(R.id.btn_tracker)
        val btnViewer = view.findViewById<RadioButton>(R.id.btn_viewer)

        subs.add(appComponent.device().getCurrentDevice().run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    textName.text = it.name
                    textManufacturer.text = it.manufacturer
                    if (it.mode == "tracker") {
                        btnTracker.isChecked = true
                    } else {
                        btnViewer.isChecked = true
                    }
                }, {
                    it.printStackTrace()
                }))
    }
}