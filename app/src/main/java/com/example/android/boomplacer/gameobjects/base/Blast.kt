package com.example.android.boomplacer.gameobjects.base

import android.graphics.Canvas
import android.graphics.Paint
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.gameobjects.MovePattern
import com.example.android.boomplacer.math.Vector2

open class Blast protected constructor(
    protected val paint: Paint,
    radius: Float,
    radiusDecreaseRateDp: Float,
    velocity: Vector2,
    position: Vector2,
    movePattern: MovePattern
) : GameObject(null, radius, velocity, position, movePattern) {
    var radiusDecreaseRatePx: Float = dpToPx(radiusDecreaseRateDp)

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(positionPx.x, positionPx.y, radiusPx, paint)
    }

    override fun updateState(
        fieldWidth: Int,
        fieldHeight: Int,
        secondsElapsed: Float,
        objectManager: ObjectManager
    ): Boolean {
        radiusPx -= radiusDecreaseRatePx * secondsElapsed
        return radiusPx <= 0
    }
}