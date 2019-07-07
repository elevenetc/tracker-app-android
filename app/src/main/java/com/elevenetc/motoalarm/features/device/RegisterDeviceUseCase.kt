package com.elevenetc.motoalarm.features.device

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.elevenetc.motoalarm.core.UseCase
import com.elevenetc.motoalarm.core.api.AccessToken
import com.elevenetc.motoalarm.core.api.Api
import com.elevenetc.motoalarm.core.cache.KeyValue
import io.reactivex.Completable
import javax.inject.Inject

class RegisterDeviceUseCase @Inject constructor(
        private val api: Api,
        private val keyValue: KeyValue,
        private val appContext: Context
) : UseCase() {
    @SuppressLint("HardwareIds")
    fun run(): Completable {
        val accessToken = keyValue.getUUID(KeyValue.Keys.ACCESS_TOKEN)
        val userId = keyValue.getUUID(KeyValue.Keys.USER_ID)
        val id = Settings.Secure.getString(appContext.contentResolver, Settings.Secure.ANDROID_ID)
        val deviceName = android.os.Build.MODEL
        val deviceMan = android.os.Build.MANUFACTURER

        return api.registerDevice(id, deviceMan, deviceName, AccessToken(accessToken), userId).flatMapCompletable {
            keyValue.setBool(KeyValue.Keys.DEVICE_REGISTERED, true)
            keyValue.setString(KeyValue.Keys.DEVICE_ID, it.id)
            Completable.complete()
        }
    }
}