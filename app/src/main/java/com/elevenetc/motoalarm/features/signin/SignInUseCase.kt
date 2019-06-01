package com.elevenetc.motoalarm.features.signin

import com.elevenetc.motoalarm.core.UseCase
import com.elevenetc.motoalarm.core.user.UserManager
import io.reactivex.Completable

class SignInUseCase(
        private val userManager: UserManager
) : UseCase() {
    fun get(email: String, password: String): Completable {
        return userManager.signIn(email, password)
    }
}