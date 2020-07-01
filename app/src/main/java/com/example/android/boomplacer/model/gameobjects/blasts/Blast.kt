package com.example.android.boomplacer.model.gameobjects.blasts

import android.graphics.Canvas
import android.graphics.Paint
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.base.GameObject
import com.example.android.boomplacer.model.gameobjects.blastpatterns.BlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern

open class Blast constructor(
    protected val paint: Paint,
    radiusPx: Float,
    var radiusChangeRate: Float,
    velocityPx: Vector2,
    positionPx: Vector2,
    movePattern: MovePattern,
    protected val blastRadiusPattern: BlastRadiusPattern
) : GameObject(null, radiusPx, velocityPx, positionPx, movePattern) {

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(positionPx.x, positionPx.y, radiusPx, paint)
    }

    override fun updateState(
        fieldWidth: Int,
        fieldHeight: Int,
        secondsElapsed: Float,
        objectManager: ObjectManager
    ): Boolean {
        blastRadiusPattern.applyPattern(this, secondsElapsed)
        return radiusPx <= 0
    }
}