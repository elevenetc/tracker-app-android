package com.elevenetc.motoalarm.core.api

import com.elevenetc.motoalarm.core.user.User
import com.elevenetc.motoalarm.features.device.Device
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*
import javax.inject.Inject

class ApiImpl @Inject constructor() : Api {

    private val api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://10.0.2.2:8080/")
            .build()
            .create(NetworkApi::class.java)

    override fun logout(accessToken: AccessToken,
                        userId: UUID): Completable {
        return api.logout(userId, accessToken.value)
    }

    override fun login(email: String, password: String): Single<Pair<AccessToken, User>> {
        return api.login(LoginDto(email, password)).map {
            Pair(AccessToken(it.accessToken), User(it.userId))
        }
    }

    override fun register(email: String, password: String): Single<Pair<AccessToken, User>> {
        return api.register(RegisterUserDto(email, password)).map {
            Pair(AccessToken(it.accessToken), User(it.userId))
        }
    }

    override fun registerDevice(hardwareId: String,
                                manufacturer: String,
                                name: String,
                                accessToken: AccessToken,
                                userId: UUID): Single<Device> {

        return api.registerDevice(CreateDeviceDto(hardwareId, manufacturer, name), userId, accessToken.value).map {
            Device()
        }
    }

    interface NetworkApi {
        @POST("/users")
        fun register(@Body body: RegisterUserDto): Single<UserDto>

        @POST("/users/login")
        fun login(@Body body: LoginDto): Single<UserDto>

        @POST("/users/{userId}/logout")
        fun logout(
                @Path("userId") userId: UUID,
                @Header("access-token") token: UUID): Completable

        @POST("users/{userId}/devices")
        fun registerDevice(
                @Body body: CreateDeviceDto,
                @Path("userId") userId: UUID,
                @Header("access-token") token: UUID
        ): Single<DeviceDto>


    }
}