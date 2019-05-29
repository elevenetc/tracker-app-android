package com.elevenetc.motoalarm.core.cache

import android.content.Context
import javax.inject.Inject

class KeyValueImpl @Inject constructor(
        context: Context
) : KeyValue {

    private val prefs = context.getSharedPreferences("main", Context.MODE_PRIVATE)

    override fun getBool(key: String, default: Boolean): Boolean {
        return prefs.getBoolean(key, default)
    }

    override fun getString(key: String, default: String): String {
        return prefs.getString(key, default)!!
    }

    override fun setBool(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    override fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }
}