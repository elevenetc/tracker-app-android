package com.elevenetc.motoalarm.features.signin

import com.elevenetc.motoalarm.core.user.User
import io.reactivex.Completable
import io.reactivex.Observable

interface SingInService {
    fun signIn(email: String, password: String): Observable<User>
    fun signOut(): Completable
}