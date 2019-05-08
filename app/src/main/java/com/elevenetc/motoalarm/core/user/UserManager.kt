package com.elevenetc.motoalarm.core.user

import io.reactivex.Completable

interface UserManager {
    fun signIn(email: String, password: String): Completable
    fun singOut(): Completable
}