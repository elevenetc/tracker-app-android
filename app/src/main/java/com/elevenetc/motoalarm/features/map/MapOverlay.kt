package com.elevenetc.motoalarm.features.map

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class MapOverlay : View {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    private lateinit var backgroundView: View
    var mode = MODE_MAP
    var inTransition = false
    lateinit var finishedTransitionHandler: () -> Unit
    lateinit var startedTransitionHandler: () -> Unit

    init {
        //alpha = 0f
        //setBackgroundColor(Color.BLUE)
    }

    private val mapPoints: MutableList<Point> = mutableListOf()
    private val listPoints: MutableList<Point> = mutableListOf()
    private val transitionPoints: MutableList<Point> = mutableListOf()

    val pointPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    val pathPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    fun setPoints(points: List<Point>) {
        this.mapPoints.clear()
        this.mapPoints.addAll(points)
        invalidate()
    }

    val path = Path()

    override fun onDraw(canvas: Canvas) {

        if (inTransition || mode == MODE_LIST) {

            path.reset()

            path.moveTo(transitionPoints[0].x.toFloat(), transitionPoints[0].y.toFloat())




            repeat(transitionPoints.size){
                val p = transitionPoints[it]

                if(it > 0){
                    path.lineTo(p.x.toFloat(), p.y.toFloat())
                }
            }

            canvas.drawPath(path, pathPaint)

            repeat(transitionPoints.size){
                val p = transitionPoints[it]
                canvas.drawCircle(p.x.toFloat(), p.y.toFloat(), 10f, pointPaint)
            }

        } else {

            if (mode == MODE_MAP) {
                canvas.drawColor(Color.TRANSPARENT)
            }
        }
    }

    fun setOnTransitionFinishedHandler(finishedTransitionHandler: () -> Unit) {
        this.finishedTransitionHandler = finishedTransitionHandler
    }

    fun setOnTransitionStartedHandler(startedTransitionHandler: () -> Unit) {
        this.startedTransitionHandler = startedTransitionHandler
    }

    fun onTransitionFinished() {
        finishedTransitionHandler()
    }

    fun toList() {

        prepareList()
        prepareTransition()
        inTransition = true
        mode = MODE_LIST

        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 500
            addUpdateListener {
                val transition = it.animatedValue as Float
                backgroundView.alpha = transition
                updateListTransition(transition)
                invalidate()
            }

            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    inTransition = false
                    onTransitionFinished()
                    invalidate()
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationStart(animation: Animator?) {
                    startedTransitionHandler()
                }

            })

            start()
        }
    }

    fun toMap() {

        prepareList()
        prepareTransition()
        mode = MODE_MAP
        inTransition = true


        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 500
            addUpdateListener {
                val transition = it.animatedValue as Float
                backgroundView.alpha = 1f - transition
                updateListTransition(transition)
                invalidate()
            }

            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    onTransitionFinished()
                    postDelayed({
                        inTransition = false
                        invalidate()
                    }, 100)
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationStart(animation: Animator?) {
                    startedTransitionHandler()
                }

            })

            start()
        }
    }

    private fun prepareList() {

        listPoints.clear()

        val left = 100
        val height = 200
        var current = height

        repeat(mapPoints.size) {
            listPoints.add(Point(left, current))
            current += height
        }
    }

    private fun prepareTransition() {

        transitionPoints.clear()

        repeat(listPoints.size) {

            if (mode == MODE_LIST) {
                val p = listPoints[it]
                transitionPoints.add(Point(p.x, p.y))
            } else {
                val p = mapPoints[it]
                transitionPoints.add(Point(p.x, p.y))
            }
        }
    }

    private fun updateListTransition(transition: Float) {

        Log.d("trans", transition.toString())

        repeat(mapPoints.size) { i ->
            val mapPoint = mapPoints[i]
            val listPoint = listPoints[i]
            val transitionPoint = transitionPoints[i]

            var fromX = 0
            var toX = 0
            var fromY = 0
            var toY = 0

            if (mode == MODE_LIST) {
                //map to list

                fromX = mapPoint.x
                toX = listPoint.x

                fromY = mapPoint.y
                toY = listPoint.y

            } else {
                //list to map

                fromX = listPoint.x
                toX = mapPoint.x

                fromY = listPoint.y
                toY = mapPoint.y
            }

            val xDiff = toX - fromX
            val yDiff = toY - fromY

            transitionPoint.x = (fromX + xDiff * transition).toInt()
            transitionPoint.y = (fromY + yDiff * transition).toInt()
        }
    }

    fun setBackgroundView(background: View) {
        this.backgroundView = background
        this.backgroundView.alpha = 0f
    }

    companion object {
        const val MODE_MAP = 1
        const val MODE_LIST = 2
    }
}