package com.elevenetc.motoalarm.features.device

import java.util.*

class Device {
    lateinit var id: UUID
    lateinit var hardwareId: String
    lateinit var name: String
    lateinit var manufacturer: String
    lateinit var mode: String
    var current: Boolean = false
}