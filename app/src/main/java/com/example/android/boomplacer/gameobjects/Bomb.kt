package com.example.android.boomplacer.gameobjects

import android.graphics.Bitmap
import android.graphics.PointF

class Bomb(
    image: Bitmap,
    private val blastRadius: Float,
    private val timeBeforeBlast: Float,
    radius: Float,
    speed: Float,
    position: PointF,
    movePattern: MovePattern
) : GameObject(image, radius, speed, position, movePattern) {

    companion object {
        fun simpleBomb(image: Bitmap, position: PointF) =
            Bomb(image, 5f, 3f, 3f, 0f, position, MovePattern.linear)
    }

    override fun updateState(timeForOneFrameMillis: Long) {
        position.set(
            movePattern.calculatePosition(
                getSpeedPerFrame(timeForOneFrameMillis),
                position
            )
        )
    }
}