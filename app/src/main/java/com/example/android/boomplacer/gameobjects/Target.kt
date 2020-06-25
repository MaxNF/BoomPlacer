package com.example.android.boomplacer.gameobjects

import android.graphics.Bitmap
import android.graphics.PointF

class Target(
    image: Bitmap,
    radius: Float,
    speed: Float,
    position: PointF,
    movePattern: MovePattern
) : GameObject(image, radius, speed, position, movePattern) {

    override fun updateState(timeForOneFrameMillis: Long) {
        position.set(
            movePattern.calculatePosition(
                getSpeedPerFrame(timeForOneFrameMillis),
                position
            )
        )
    }
}