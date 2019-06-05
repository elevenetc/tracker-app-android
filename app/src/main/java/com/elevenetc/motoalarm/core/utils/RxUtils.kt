package com.elevenetc.motoalarm.core.utils

import android.view.View
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


object RxUtils {
    fun <T> btnObservable(btn: View, provider: () -> T): Observable<T> {
        val subject = PublishSubject.create<T>()
        btn.setOnClickListener {
            subject.onNext(provider())
        }
        return subject
    }

    fun scheduler(): CompletableTransformer {
        return object : CompletableTransformer {
            override fun apply(upstream: Completable): CompletableSource {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}