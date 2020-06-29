package com.example.android.boomplacer.model.gameobjects.targets

import android.graphics.Bitmap
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.GameObject
import com.example.android.boomplacer.model.gameobjects.blasts.Blast
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import kotlin.random.Random

open class Target constructor(
    bitmap: Bitmap,
    val score: Int,
    radiusPx: Float,
    velocityPx: Vector2,
    positionPx: Vector2,
    movePattern: MovePattern
) : GameObject(bitmap, radiusPx, velocityPx, positionPx, movePattern) {

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
}