package com.elevenetc.motoalarm.core.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class BusImpl @Inject constructor(){

    private val subject = PublishSubject.create<Any>()

    fun post(event: Any) {
        subject.onNext(subject)
    }

    fun get(): Observable<Any> {
        return subject
    }
}