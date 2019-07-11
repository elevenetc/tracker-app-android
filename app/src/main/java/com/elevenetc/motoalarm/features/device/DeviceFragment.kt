package com.elevenetc.motoalarm.features.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.ui.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class DeviceFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadDevice(view)
    }

    private fun loadDevice(view: View) {
        val textName = view.findViewById<TextView>(R.id.text_name)
        val textManufacturer = view.findViewById<TextView>(R.id.text_manufacturer)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
        val btnTracker = view.findViewById<RadioButton>(R.id.btn_tracker)
        val btnViewer = view.findViewById<RadioButton>(R.id.btn_viewer)
        val dataContainer = view.findViewById<View>(R.id.data_container)
        val progressView = view.findViewById<View>(R.id.progress_view)
        val btnRetry = view.findViewById<View>(R.id.btn_retry)


        btnRetry.setOnClickListener {
            loadDevice(view)
        }

        progressView.visibility = View.VISIBLE
        dataContainer.visibility = View.GONE
        btnRetry.visibility = View.GONE

        val deviceId = arguments!!.getSerializable(KeyValue.Keys.DEVICE_ID) as UUID

        subs.add(appComponent.device().getDevice().run(deviceId)
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

                    progressView.visibility = View.GONE
                    dataContainer.visibility = View.VISIBLE
                    btnRetry.visibility = View.GONE

                }, {
                    it.printStackTrace()

                    progressView.visibility = View.GONE
                    dataContainer.visibility = View.GONE
                    btnRetry.visibility = View.VISIBLE
                }))
    }

    companion object {
        fun newInstance(deviceId: UUID): DeviceFragment {
            return DeviceFragment().apply {
                this.arguments = Bundle().apply {
                    this.putSerializable(KeyValue.Keys.DEVICE_ID, deviceId)
                }
            }
        }
    }
}