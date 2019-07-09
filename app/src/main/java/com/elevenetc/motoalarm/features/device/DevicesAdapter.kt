package com.elevenetc.motoalarm.features.device

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.elevenetc.motoalarm.R
import java.util.*

class DevicesAdapter(val devices: List<Device>, val onDevicesClick:(deviceId:UUID) -> Unit) : RecyclerView.Adapter<DevicesAdapter.VH>() {

    override fun onCreateViewHolder(container: ViewGroup, type: Int): VH {
        return VH(LayoutInflater.from(container.context).inflate(R.layout.item_device, container, false))
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    override fun onBindViewHolder(vh: VH, id: Int) {
        val device = devices[id]
        vh.itemView.findViewById<TextView>(R.id.text_name).text = device.name
        vh.itemView.findViewById<TextView>(R.id.text_manufacturer).text = device.manufacturer
        vh.itemView.findViewById<CheckBox>(R.id.check_current_device).isChecked = device.current
        vh.itemView.setOnClickListener {
            onDevicesClick(device.id)
        }
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {

    }
}