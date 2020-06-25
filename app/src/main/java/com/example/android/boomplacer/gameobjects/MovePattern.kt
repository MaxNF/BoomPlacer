package com.example.android.boomplacer.gameobjects

import android.graphics.PointF

class MovePattern(private val function: (speed: Float, position: PointF) -> PointF) {
    companion object {
        val linear: MovePattern = MovePattern { speed, position ->
            PointF()
        }
    }

    fun calculatePosition(speed: Float, position: PointF): PointF {
        return function(speed, position)
    }
}