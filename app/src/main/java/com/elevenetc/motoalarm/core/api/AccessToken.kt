package com.elevenetc.motoalarm.core.api

import java.util.*

class AccessToken {
    val value: UUID

    constructor(value: UUID) {
        this.value = value
    }

    constructor(value: String) {
        this.value = UUID.fromString(value)
    }

}