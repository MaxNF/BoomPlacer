package com.example.android.boomplacer.model.gameobjects.base

import android.graphics.Bitmap
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.math.Vector2

open class Bomb protected constructor(
    image: Bitmap,
    val blast: Blast,
    private var timeBeforeBlast: Float,
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
        updateTimerState(secondsElapsed)
        return if (timeBeforeBlast <= 0) {
            objectManager.placeBlast(this)
            true
        } else {
            false
        }
    }

    open protected fun updateTimerState(secondsElapsed: Float) {
        timeBeforeBlast -= secondsElapsed
    }

    open fun calculateScore(): Int {
        return 1
    }
}