package com.elevenetc.motoalarm.core.api

import java.util.*

data class UserDto(
        val accessToken: UUID,
        val userId: UUID,
        val motorcycles: List<MotoDto> = emptyList(),
        val devices: List<DeviceDto> = emptyList()
)