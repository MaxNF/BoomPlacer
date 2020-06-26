package com.example.android.boomplacer.gameobjects

import android.graphics.Bitmap
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.math.Vector2

class Bomb private constructor(
    image: Bitmap,
    val blast: Blast,
    private var timeBeforeBlast: Float,
    radius: Float,
    velocity: Vector2,
    position: Vector2,
    movePattern: MovePattern
) : GameObject(image, radius, velocity, position, movePattern) {


    companion object {
        fun simpleStaticBomb(image: Bitmap, blast: Blast) =
            normalizedBomb(image, blast, 0.5f, 8f, Vector2.zero(), Vector2.zero(), MovePattern.static)

        private fun normalizedBomb(
            image: Bitmap,
            blast: Blast,
            timeBeforeBlast: Float,
            radius: Float,
            velocity: Vector2,
            position: Vector2,
            movePattern: MovePattern
        ): Bomb {
            velocity.apply {
                x = dpToPx(x)
                y = dpToPx(y)
            }
            position.apply {
                x = dpToPx(x)
                y = dpToPx(y)
            }
            return Bomb(
                image,
                blast,
                timeBeforeBlast,
                dpToPx(radius),
                velocity,
                position,
                movePattern
            )
        }
    }

    override fun updateState(
        fieldWidth: Int,
        fieldHeight: Int,
        secondsElapsed: Float
    ): Boolean {
        super.updateState(fieldWidth, fieldHeight, secondsElapsed)
        timeBeforeBlast -= secondsElapsed
        if (timeBeforeBlast <= 0) {
            return false
        }
        return true
    }
}