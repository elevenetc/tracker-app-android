package com.elevenetc.motoalarm.features.moto

import com.elevenetc.motoalarm.features.device.Device
import java.util.*

class Motorcycle {
    lateinit var id: UUID
    lateinit var name: String
    lateinit var devices: List<Device>
}