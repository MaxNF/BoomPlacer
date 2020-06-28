package com.example.android.boomplacer.model.gameobjects.base

import android.graphics.Bitmap
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.math.Vector2

open class Target protected constructor(
    image: Bitmap,
    radius: Float,
    velocity: Vector2,
    position: Vector2,
    movePattern: MovePattern
) : GameObject(image, radius, velocity, position, movePattern) {

    override fun updateState(
        fieldWidth: Int,
        fieldHeight: Int,
        secondsElapsed: Float,
        objectManager: ObjectManager
    ): Boolean {
        super.updateState(fieldWidth, fieldHeight, secondsElapsed, objectManager)
        return isDestroyed(objectManager.placedBlasts)
    }

    open protected fun isDestroyed(blasts: List<Blast>): Boolean {
        blasts.forEach { blast ->
            if (positionPx.distanceTo(blast.positionPx) <= (radiusPx + blast.radiusPx)) {
                return true
            }
        }
        return false
    }

    open fun calculateScore(): Int {
        return 1
    }
}