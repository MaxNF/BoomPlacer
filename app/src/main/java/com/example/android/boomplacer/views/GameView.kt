package com.example.android.boomplacer.views

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.SurfaceView
import com.example.android.boomplacer.gameobjects.GameObject

class GameView(context: Context) : SurfaceView(context), Runnable {
    private val TAG = "GameView"

    private val gameThread: Thread = Thread(this)
    private var running = false
    private val gameObjects = mutableListOf<GameObject>()
    var targetFramerate: Int = 60
    var showFramerate = false
    var fpsPaint = Paint()
    private var frameCount = 0
    private var totalTime = 0L

    fun startGame() {
        running = true
        gameThread.start()
    }

    fun stopGame() {
        running = false
    }

    override fun run() {
        var startTimeNano: Long
        var differenceTimeMillis: Long
        var targetTimeMillis: Long = 1000L / targetFramerate
        var waitTimeMillis: Long

        while (running) {
            startTimeNano = System.nanoTime()

            updateGameState(targetTimeMillis)
            draw()

            differenceTimeMillis = (System.nanoTime() - startTimeNano) / 1000000
            waitTimeMillis = targetTimeMillis - differenceTimeMillis
            Thread.sleep(waitTimeMillis)

            if (showFramerate) {
                calculateAndPrintFramerate(startTimeNano)
            }
        }
    }

    private fun calculateAndPrintFramerate(startTimeNano: Long) {
        totalTime += System.nanoTime() - startTimeNano
        frameCount++
        if (frameCount == targetFramerate) {
            Log.d(TAG, "FPS: ${1000 / ((totalTime / frameCount) / 1000000)}")
            frameCount = 0
            totalTime = 0
        }
    }

    private fun updateGameState(timeForOneFrameMillis: Long) {
        gameObjects.forEach { it.updateState(timeForOneFrameMillis) }
    }

    private fun draw() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawColor(Color.LTGRAY)
            gameObjects.forEach { it.draw(canvas) }
            holder.unlockCanvasAndPost(canvas)
        }
    }
}