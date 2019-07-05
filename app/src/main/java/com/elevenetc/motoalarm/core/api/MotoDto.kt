package com.elevenetc.motoalarm.core.api

import com.elevenetc.motoalarm.features.moto.Motorcycle
import java.util.*

class MotoDto {

    lateinit var id: UUID
    lateinit var name: String
    lateinit var deviceStates: List<DeviceDto>

    constructor(from: Motorcycle) {
        id = from.id
        name = from.name
        deviceStates = from.devices.map { DeviceDto(it) }
    }

    constructor()
}