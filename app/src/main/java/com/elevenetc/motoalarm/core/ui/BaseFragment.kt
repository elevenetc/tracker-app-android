package com.elevenetc.motoalarm.core.ui

import android.support.v4.app.Fragment
import com.elevenetc.motoalarm.core.app.App
import com.elevenetc.motoalarm.core.app.AppComponent
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {
    val appComponent: AppComponent by lazy { (context!!.applicationContext as App).appComponent }
    val subs = CompositeDisposable()
}