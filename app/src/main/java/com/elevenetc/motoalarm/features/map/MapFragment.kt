package com.elevenetc.motoalarm.features.map

import android.annotation.SuppressLint
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Button
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*


class MapFragment : BaseFragment(), OnMapReadyCallback {


    lateinit var mapView: MapView
    lateinit var map: GoogleMap
    lateinit var btnConvert: Button
    lateinit var overlayView: MapOverlay
    lateinit var background: View

    val latlgns = mutableListOf<LatLng>()
    val markers = mutableListOf<Marker>()
    val lines = mutableListOf<Polyline>()

    override fun onMapReady(map: GoogleMap) {

        this.map = map

        if (mapView.viewTreeObserver.isAlive) {
            mapView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    mapView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    // set map viewport
                    // CENTER is LatLng object with the center of the map
                    //map.moveCamera(CameraUpdateFactory.newLatLngZoom(CENTER, 15f))
                    // ! you can query Projection object here

                    // ! example output in my test code: (356, 483)
                    //System.out.println(markerScreenPosition)
                    initCamera()
                }
            })
        }
    }

    private fun initCamera() {

        val markerBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(markerBitmap)

//        canvas.drawRect(0f, 0f, 100f, 100f, Paint().apply {
//            style = Paint.Style.FILL
//            color = Color.BLUE
//        })

        canvas.drawCircle(50f, 50f, 10f, Paint().apply {
            style = Paint.Style.FILL
            color = Color.RED
        })





        latlgns.add(LatLng(43.1, -87.9))
        latlgns.add(LatLng(43.12, -87.91))
        latlgns.add(LatLng(43.3, -87.93))
        latlgns.add(LatLng(43.5, -87.98))
        latlgns.add(LatLng(43.6, -87.999))
        latlgns.add(LatLng(43.78, -88.0))
        latlgns.add(LatLng(43.88, -88.5))
        latlgns.add(LatLng(43.98, -88.9))

        //val from = LatLng(43.1, -87.9)
        //val to = LatLng(43.12, -87.95)

        latlgns.forEach {
            markers.add(map.addMarker(MarkerOptions().apply {
                this.position(it)
                this.icon(BitmapDescriptorFactory.fromBitmap(markerBitmap))
                this.anchor(0.5f, 0.5f)
            }))


        }

        lines.add(map.addPolyline(PolylineOptions().apply {
            latlgns.forEach { latlng ->
                add(latlng)
                width(5f)
            }
        }))

//        val markerFrom = map.addMarker(MarkerOptions().apply {
//            this.position(from)
//        })
//
//        val markerTo = map.addMarker(MarkerOptions().apply {
//            this.position(to)
//        })

        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(from, 11f))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlgns[0], 9f))

        map.setOnCameraMoveListener {
            overlayView.setPoints(getPoints(markers))
        }


//        map.addPolyline(PolylineOptions().apply {
//            this.add(from)
//            this.add(to)
//        })

        mapView.postDelayed({

            overlayView.setPoints(getPoints(markers))

        }, 1000)


        btnConvert.setOnClickListener {
            if (overlayView.mode == MapOverlay.MODE_MAP) {

                btnConvert.text = "to map"
                overlayView.toList()
            } else {
                btnConvert.text = "to list"
                overlayView.toMap()
            }
        }

    }

    private fun getPoints(markers: List<Marker>): List<Point> {
        val result = mutableListOf<Point>()
        markers.forEach {
            result.add(map.projection.toScreenLocation(it.position))
        }

        return result.reversed()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val result = inflater.inflate(R.layout.fragment_map, container, false)

        overlayView = result.findViewById(R.id.overlay_view)
        btnConvert = result.findViewById(R.id.btn_convert)
        mapView = result.findViewById(R.id.map_view)
        background = result.findViewById(R.id.background)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        overlayView.setBackgroundView(background)

        overlayView.setOnTransitionStartedHandler {
            if (overlayView.mode == MapOverlay.MODE_LIST) {
                markers.forEach {
                    it.isVisible = false
                }


                lines.forEach {
                    it.isVisible = false
                }

            } else {

            }
        }

        overlayView.setOnTransitionFinishedHandler {
            if (overlayView.mode == MapOverlay.MODE_LIST) {

            } else {
                markers.forEach {
                    it.isVisible = true
                }

                lines.forEach {
                    it.isVisible = true
                }
            }
        }

        return result
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object {
        fun new(): MapFragment {
            return MapFragment()
        }
    }
}