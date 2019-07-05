package com.elevenetc.motoalarm.features.login

import com.elevenetc.motoalarm.core.api.Api
import com.elevenetc.motoalarm.core.cache.KeyValue
import com.elevenetc.motoalarm.core.mvi.ViewIntent
import com.elevenetc.motoalarm.core.navigation.HomeCoordinator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LogInViewModel @Inject constructor(
        private val api: Api,
        private val keyValue: KeyValue,
        private val homeCoordinator: HomeCoordinator
) {

    val intentsSubject = PublishSubject.create<ViewIntent>()

    val states = intentsSubject.flatMap {

        when (it) {
            is Intents.LogIn -> Observable.concat(Observable.just(States.Progress),
                    LoginUseCase(api, keyValue).get(it.email, it.password)
                            .toSingleDefault(States.Success)
                            .cast(States::class.java)
                            .toObservable()
                            .onErrorReturn { e -> States.Error(e) }
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
            )
            is Intents.Register -> Observable.concat(Observable.just(States.Progress),
                    RegisterUseCase(api, keyValue).get(it.email, it.password)
                            .toSingleDefault(States.Success)
                            .cast(States::class.java)
                            .toObservable()
                            .onErrorReturn { e -> States.Error(e) }
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
            )
            else -> throw RuntimeException("Not supported intent: $it")
        }
    }

    init {
//        states.filter {
//            it is States.Success
//        }.observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    homeCoordinator.start()
//                }
    }

    fun intent(intent: Observable<ViewIntent>) {
        intent.subscribe(intentsSubject)
    }

    fun stateStream(): Observable<States> {
        return states.cast(States::class.java)
    }

    sealed class Intents : ViewIntent() {
        data class LogIn(val email: String, val password: String) : Intents()
        data class Register(val email: String, val password: String) : Intents()
    }

    sealed class States {
        object Progress : States()
        object Success : States()
        class Error(val error: Throwable) : States()
    }
}