package com.elevenetc.motoalarm.core.api

import com.elevenetc.motoalarm.core.cache.KeyValue
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
        private val keyValue: KeyValue
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        var builder = request.newBuilder()

        if (keyValue.contains(KeyValue.Keys.ACCESS_TOKEN)) {
            val accessToken = keyValue.getString(KeyValue.Keys.ACCESS_TOKEN)
            request = builder
                    .addHeader("access-token", accessToken)
                    .build()
        }
        return chain.proceed(request)
    }
}