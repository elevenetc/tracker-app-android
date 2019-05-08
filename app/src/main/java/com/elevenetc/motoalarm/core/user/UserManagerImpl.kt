package com.elevenetc.motoalarm.core.user

import io.reactivex.Completable

class UserManagerImpl : UserManager {
    override fun signIn(email: String, password: String): Completable {
        return Completable.complete()
    }

    override fun singOut(): Completable {
        return Completable.complete()
    }
}