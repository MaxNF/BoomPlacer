package com.example.android.boomplacer.model.gameobjects.base

import android.graphics.Bitmap
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.math.Vector2
import kotlin.random.Random

open class Target protected constructor(
    image: Bitmap,
    radiusDp: Float,
    speedDp: Float,
    fieldWidth: Int,
    fieldHeight: Int,
    movePattern: MovePattern,
    radiusModifier: Float,
    speedModifier: Float
) : GameObject(
    image,
    radiusDp * radiusModifier,
    Vector2.create(Random.nextInt(360).toFloat(), speedDp * speedModifier),
    Vector2.createRandom(fieldWidth, fieldHeight),
    movePattern
) {

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