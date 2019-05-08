package com.elevenetc.motoalarm.features.signin

import com.elevenetc.motoalarm.core.mvi.ViewIntent
import com.elevenetc.motoalarm.core.user.UserManager
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SignInViewModel(
        private val userManager: UserManager
) {

    val ps = BehaviorSubject.create<States>()

    fun intent(intent: ViewIntent) {

        if (intent is Intents.SignIn) {
            ps.onNext(States.SignInProgress)
            val signInObservable = userManager.signIn(intent.email, intent.password)
                    .toObservable<States>()

            signInObservable.subscribe(ps)

//                    .subscribe({
//                        ps.onNext(States.SignInSuccess)
//                    }, {
//                        ps.onNext(States.Error)
//                    })
        }

    }

    fun stateStream(): Observable<States> {
        return ps.publish().autoConnect()
    }

    sealed class Intents : ViewIntent() {
        data class SignIn(val email: String, val password: String) : Intents()
    }

    sealed class States {
        object Idle : States()
        object SignInProgress : States()
        object SignInSuccess : States()
        object Error : States()
    }
}