package com.elevenetc.motoalarm.features.device

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DevicesFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_devices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getDevices()
    }

    private fun getDevices() {

        val btnRetry = view!!.findViewById<Button>(R.id.btn_retry)
        val progressView = view!!.findViewById<View>(R.id.progress_view)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recycler_view)

        btnRetry.visibility = View.GONE
        progressView.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        btnRetry.setOnClickListener { getDevices() }

        subs.add(appComponent.device().getDevices()
                .run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    btnRetry.visibility = View.GONE
                    progressView.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE

                    recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = DevicesAdapter(it)


                }, {
                    it.printStackTrace()
                    btnRetry.visibility = View.VISIBLE
                    progressView.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }))
    }

    companion object {
        fun new(): DevicesFragment {
            return DevicesFragment()
        }
    }
}