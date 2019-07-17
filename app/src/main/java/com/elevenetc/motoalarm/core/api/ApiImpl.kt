package com.elevenetc.motoalarm.core.api

import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.location.Loc
import com.elevenetc.motoalarm.core.user.User
import com.elevenetc.motoalarm.features.device.Device
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*
import javax.inject.Inject

class ApiImpl @Inject constructor(
        private val keyValue: KeyValue,
        authInterceptor: AuthInterceptor
) : Api {

    private val api = Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(authInterceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://10.0.2.2:8080/")
            .build()
            .create(NetworkApi::class.java)

    override fun logout(): Completable {
        val userId = keyValue.getUUID(KeyValue.Keys.USER_ID)
        return api.logout(userId)
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
                                name: String): Single<Device> {

        val userId = keyValue.getUUID(KeyValue.Keys.USER_ID)

        return api.registerDevice(CreateDeviceDto(hardwareId, manufacturer, name), userId).map {
            Device().apply {
                this.id = it.id!!
                this.name = it.name
                this.mode = ""
                this.manufacturer = it.manufacturer
                this.hardwareId = it.hardwareId
            }
        }
    }

    override fun getDevices(): Single<List<Device>> {

        val userId = keyValue.getUUID(KeyValue.Keys.USER_ID)

        return api.getDevices(userId).map {
            it.map {
                Device().apply {
                    this.hardwareId = it.hardwareId
                    this.manufacturer = it.manufacturer
                    this.mode = it.mode
                    this.name = it.name
                    this.id = it.id!!
                }
            }
        }
    }

    override fun getDevice(deviceId: UUID): Single<Device> {

        val userId = keyValue.getUUID(KeyValue.Keys.USER_ID)

        return api.getDevice(userId, deviceId).map {
            Device().apply {
                this.hardwareId = it.hardwareId
                this.manufacturer = it.manufacturer
                this.mode = it.mode
                this.name = it.name
                this.id = it.id!!
            }
        }
    }

    override fun updateDeviceMode(deviceId: UUID, mode: String): Completable {
        val userId = keyValue.getUUID(KeyValue.Keys.USER_ID)
        return api.setMode(UpdateMode(mode), userId, deviceId)
    }

    override fun postState(loc: Loc, battery: Float, deviceId: UUID): Completable {
        val userId = keyValue.getUUID(KeyValue.Keys.USER_ID)
        return api.updateState(UpdateState(loc.lat, loc.lon, battery), userId, deviceId)
    }

    interface NetworkApi {
        @POST("/users")
        fun register(@Body body: RegisterUserDto): Single<UserDto>

        @POST("/users/login")
        fun login(@Body body: LoginDto): Single<UserDto>

        @POST("/users/{userId}/logout")
        fun logout(@Path("userId") userId: UUID): Completable

        @POST("users/{userId}/devices")
        fun registerDevice(
                @Body body: CreateDeviceDto,
                @Path("userId") userId: UUID
        ): Single<DeviceDto>

        @GET("users/{userId}/devices")
        fun getDevices(@Path("userId") userId: UUID): Single<List<DeviceDto>>

        @GET("users/{userId}/devices/{deviceId}")
        fun getDevice(
                @Path("userId") userId: UUID,
                @Path("deviceId") deviceId: UUID
        ): Single<DeviceDto>

        @POST("users/{userId}/devices/{deviceId}/mode")
        fun setMode(
                @Body body: UpdateMode,
                @Path("userId") userId: UUID,
                @Path("deviceId") deviceId: UUID
        ): Completable

        @PATCH("users/{userId}/devices/{deviceId}/state")
        fun updateState(
                @Body body: UpdateState,
                @Path("userId") userId: UUID,
                @Path("deviceId") deviceId: UUID
        ): Completable
    }


}