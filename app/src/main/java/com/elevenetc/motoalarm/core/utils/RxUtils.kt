package com.elevenetc.motoalarm.core.utils

import android.view.View
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


class RxUtils {

    companion object {
        fun <T> btnObservable(btn: View, provider: () -> T): Observable<T> {
            val subject = PublishSubject.create<T>()
            btn.setOnClickListener {
                subject.onNext(provider())
            }
            return subject
        }

        fun scheduler(): CompletableTransformer {
            return CompletableTransformer { upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
        }
    }

}