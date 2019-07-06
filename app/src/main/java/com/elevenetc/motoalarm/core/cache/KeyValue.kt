package com.elevenetc.motoalarm.core.cache

import java.util.*

interface KeyValue {
    fun getBool(key: String, default: Boolean = false): Boolean
    fun setBool(key: String, value: Boolean)
    fun setString(key: String, value: String)
    fun setString(key: String, value: Any)
    fun getString(key: String, default: String = ""): String
    fun getUUID(key: String): UUID
    fun clear()

    object Keys {
        const val LOGGED_IN = "logged-in"
        const val USER_ID = "user-id"
        const val ACCESS_TOKEN = "access-token"
        const val DEVICE_REGISTERED = "device-registered"
    }
}