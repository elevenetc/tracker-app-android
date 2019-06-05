package com.elevenetc.motoalarm.core.user

import io.reactivex.Completable

interface UserManager {
    fun signedIn(): Boolean
    fun signIn(email: String, password: String): Completable
    fun signOut(): Completable
}