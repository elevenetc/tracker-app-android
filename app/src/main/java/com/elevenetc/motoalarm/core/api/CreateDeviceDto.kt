package com.elevenetc.motoalarm.core.api

data class CreateDeviceDto(
        val hardwareId: String,
        val manufacturer: String,
        val name: String
)