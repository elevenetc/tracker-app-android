package com.elevenetc.motoalarm.features.device

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.ui.BaseActivity
import java.util.*

class DeviceActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)
        if (savedInstanceState == null) {

            val deviceId = intent!!.extras!!.getSerializable(KeyValue.Keys.DEVICE_ID) as UUID

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, DeviceFragment.newInstance(deviceId))
                    .commit()
        }
    }

    companion object {
        fun start(deviceId: UUID, context: Context) {
            val intent = Intent(context, DeviceActivity::class.java)
            intent.putExtra(KeyValue.Keys.DEVICE_ID, deviceId)
            context.startActivity(intent)
        }
    }
}