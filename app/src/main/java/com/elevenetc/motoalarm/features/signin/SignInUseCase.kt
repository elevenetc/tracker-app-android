package com.elevenetc.motoalarm.features.signin

import android.util.Log
import com.elevenetc.motoalarm.core.UseCase
import com.elevenetc.motoalarm.core.user.UserManager
import io.reactivex.Completable

class SignInUseCase(
        private val userManager: UserManager
) : UseCase() {
    fun get(email: String, password: String): Completable {
        return Completable.fromAction {

            userManager.signIn(email, password)
        }
    }
}