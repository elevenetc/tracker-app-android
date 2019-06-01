package com.elevenetc.motoalarm.core.user

import com.elevenetc.motoalarm.core.cache.KeyValue
import io.reactivex.Completable
import javax.inject.Inject

class UserManagerImpl @Inject constructor(
        private val keyValue: KeyValue
) : UserManager {

    override fun signIn(email: String, password: String): Completable {
        return Completable.fromAction {

            Thread.sleep(1000)

            if (email != "z") {
                throw EmailOrPasswordInvalid()
            } else {
                keyValue.setBool(KeyValue.Keys.SIGNED_IN, true)
            }
        }
    }

    override fun signedIn(): Boolean {
        return keyValue.getBool(KeyValue.Keys.SIGNED_IN)
    }

    override fun singOut(): Completable {
        return Completable.fromAction {
            Thread.sleep(1000)
            keyValue.setBool(KeyValue.Keys.SIGNED_IN, false)
        }
    }

}