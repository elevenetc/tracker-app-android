package com.elevenetc.motoalarm.core.utils

import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


object RxUtils {
    fun <T> btnObservable(btn: View, provider: () -> T): Observable<T> {
        val subject = PublishSubject.create<T>()
        btn.setOnClickListener {
            subject.onNext(provider())
        }
        return subject
    }
}