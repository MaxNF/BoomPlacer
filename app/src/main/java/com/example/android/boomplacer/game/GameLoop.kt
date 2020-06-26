package com.example.android.boomplacer.game

import android.util.Log

class GameLoop(private val gameView: GameView) : Thread() {
    private val TAG = "GameLoop"
    var running: Boolean = false
    var paused: Boolean = false
    private var frameCount = 0
    private var totalTime = 0L


    override fun run() {
        var startTimeNano: Long
        var differenceTimeMillis: Long
        var oneFrameMillis: Long = 1000L / gameView.targetFramerate
        var waitTimeMillis: Long

        while (running) {
            while (paused) {
                sleep(100)
            }
            startTimeNano = System.nanoTime()

            gameView.updateGameState(oneFrameMillis)
            gameView.draw()

            differenceTimeMillis = (System.nanoTime() - startTimeNano) / 1000000
            waitTimeMillis = oneFrameMillis - differenceTimeMillis
            if (waitTimeMillis > 0) {
                sleep(waitTimeMillis)
            }

            if (gameView.showFramerate) {
                calculateAndPrintFramerate(startTimeNano)
            }
        }
        Log.d(TAG, "GAME FINISHED")
    }

    private fun calculateAndPrintFramerate(startTimeNano: Long) {
        totalTime += System.nanoTime() - startTimeNano
        frameCount++
        if (frameCount == gameView.targetFramerate) {
            Log.d(TAG, "FPS: ${1000 / ((totalTime / frameCount) / 1000000)}")
            frameCount = 0
            totalTime = 0
        }
    }
}