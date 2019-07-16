package com.elevenetc.motoalarm.core.location

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.elevenetc.motoalarm.core.app.App
import com.elevenetc.motoalarm.features.home.HomeActivity
import com.google.android.gms.location.*
import java.util.*

class LocationService : Service() {

    private val locClient by lazy { LocationServices.getFusedLocationProviderClient(this) }

    private val listener = object : LocationCallback() {
        override fun onLocationResult(l: LocationResult) {
            Log.d("loc", l.toString())
            val loc = Loc(l.lastLocation.latitude, l.lastLocation.longitude)
            (application as App).appComponent.bus().post(loc)
        }

        private fun getNow(): String {
            val current = Date(System.currentTimeMillis())
            val cal = Calendar.getInstance()
            cal.time = current
            val min = cal.get(Calendar.MINUTE)
            val hour = cal.get(Calendar.HOUR)
            val date = cal.get(Calendar.DATE)
            val month = cal.get(Calendar.MONTH)
            val year = cal.get(Calendar.YEAR)
            val time = "$hour:$min-$date.$month.$year"
            return time
        }

        override fun onLocationAvailability(p0: LocationAvailability) {
            Log.d("loc", p0.toString())
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        showNotification()

        val request = LocationRequest()
        request.interval = 1000 * 10
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locClient.requestLocationUpdates(request, listener, null)
    }

    private fun showNotification() {

        val channelId = "tracker-id"

        createNotificationChannel(channelId, "Tracker channel")

        val intent = Intent(this, HomeActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


        val notification = Notification.Builder(this, channelId)
                .setContentTitle("Notif Title")
                .setContentText("Notif Content")
                //.setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                //.setTicker(getText(R.string.ticker_text))
                .build()

        startForeground(100, notification)
    }

    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE)
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}