package com.elevenetc.motoalarm.features.signin

import com.elevenetc.motoalarm.core.mvi.ViewIntent
import com.elevenetc.motoalarm.core.user.UserManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class SignInViewModel(
        private val userManager: UserManager
) {

    val intentsObserver = PublishSubject.create<ViewIntent>()

    val states = intentsObserver.flatMap {


        when (it) {
            is Intents.SignIn -> Observable.concat(Observable.just(States.Progress),
                    SignInUseCase(userManager).get(it.email, it.password)
                            .toSingleDefault(States.Success)
                            .cast(States::class.java)
                            .toObservable()
                            .onErrorReturn { States.Error }
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
            )
            else -> throw RuntimeException("Not supported intent: $it")
        }
    }

    fun intent(intent: Observable<ViewIntent>) {
        intent.subscribe(intentsObserver)
    }

    fun stateStream(): Observable<States> {
        return states.cast(States::class.java)
    }

    sealed class Intents : ViewIntent() {
        data class SignIn(val email: String, val password: String) : Intents()
    }

    sealed class States {
        object Progress : States()
        object Success : States()
        object Error : States()
    }
}