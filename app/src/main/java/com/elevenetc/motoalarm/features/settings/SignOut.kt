package com.elevenetc.motoalarm.features.settings

import com.elevenetc.motoalarm.core.UseCase
import com.elevenetc.motoalarm.core.user.UserManager
import io.reactivex.Completable
import javax.inject.Inject

class SignOut @Inject constructor(
        private val userManager: UserManager
) : UseCase() {
    fun run(): Completable {
        return userManager.signOut()
    }
}