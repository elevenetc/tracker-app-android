package com.elevenetc.motoalarm.features.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.ui.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class DeviceFragment : BaseFragment() {

    private var deviceMode = ""
    private var newMode = ""
    private lateinit var deviceId: UUID
    private lateinit var dataContainer: View
    private lateinit var progressView: View
    private lateinit var btnRetry: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val btnTracker = view.findViewById<RadioButton>(R.id.btn_tracker)
        val btnViewer = view.findViewById<RadioButton>(R.id.btn_viewer)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)

        val btnSave = view.findViewById<View>(R.id.btn_save)

        btnRetry = view.findViewById(R.id.btn_retry)
        dataContainer = view.findViewById(R.id.data_container)
        progressView = view.findViewById(R.id.progress_view)

        btnSave.isEnabled = false

        btnSave.setOnClickListener { updateMode() }

        btnRetry.setOnClickListener {
            loadDevice(view)
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            newMode = deviceMode

            if (checkedId == btnTracker.id) {

                if (deviceMode == "viewer") {
                    newMode = "tracker"
                }

            } else if (checkedId == btnViewer.id) {
                if (deviceMode == "tracker") {
                    newMode = "viewer"
                }
            }

            btnSave.isEnabled = newMode != deviceMode
        }

        loadDevice(view)
    }

    private fun updateMode() {

        showProgress()

        subs.add(components.device().updateMode().setMode(deviceId, newMode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    deviceMode = newMode
                    showContent()
                }, {
                    it.printStackTrace()
                    showErrorOnUpdateDevice()
                }))
    }

    private fun loadDevice(view: View) {

        val textName = view.findViewById<TextView>(R.id.text_name)
        val textManufacturer = view.findViewById<TextView>(R.id.text_manufacturer)
        val btnTracker = view.findViewById<RadioButton>(R.id.btn_tracker)
        val btnViewer = view.findViewById<RadioButton>(R.id.btn_viewer)

        showProgress()

        deviceId = arguments!!.getSerializable(KeyValue.Keys.DEVICE_ID) as UUID

        subs.add(components.device().getDevice().run(deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    deviceMode = it.mode

                    textName.text = it.name
                    textManufacturer.text = it.manufacturer
                    if (it.mode == "tracker") {
                        btnTracker.isChecked = true
                    } else {
                        btnViewer.isChecked = true
                    }

                    showContent()

                }, {
                    it.printStackTrace()

                    showErrorOnData()
                }))
    }

    private fun showErrorOnUpdateDevice() {

        Toast.makeText(context, "Error device updating", Toast.LENGTH_LONG).show()

        progressView.visibility = View.GONE
        dataContainer.visibility = View.VISIBLE
    }

    private fun showErrorOnData() {

        Toast.makeText(context, "Error data loading", Toast.LENGTH_LONG).show()

        progressView.visibility = View.GONE
        dataContainer.visibility = View.GONE
        btnRetry.visibility = View.VISIBLE
    }

    private fun showContent() {
        progressView.visibility = View.GONE
        dataContainer.visibility = View.VISIBLE
        btnRetry.visibility = View.GONE
    }

    private fun showProgress() {
        progressView.visibility = View.VISIBLE
        dataContainer.visibility = View.GONE
        btnRetry.visibility = View.GONE
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