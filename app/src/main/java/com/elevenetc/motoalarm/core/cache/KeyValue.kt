package com.elevenetc.motoalarm.core.cache

interface KeyValue {
    fun getBool(key: String, default: Boolean = false): Boolean
    fun getString(key: String, default: String = ""): String
    fun setBool(key: String, value: Boolean)
    fun setString(key: String, value: String)

    object Keys {
        val SIGNED_IN = "signedIn"
    }
}