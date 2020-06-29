package com.example.android.boomplacer.model.gameobjects.bombs

import android.graphics.Bitmap
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.blasts.Blast
import com.example.android.boomplacer.model.gameobjects.GameObject
import com.example.android.boomplacer.model.gameobjects.bombpatterns.BombTimePattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern

open class Bomb constructor(
    bitmap: Bitmap,
    val blast: Blast,
    var secondsBeforeBlast: Float,
    val score: Int,
    radiusPx: Float,
    velocityPx: Vector2,
    positionPx: Vector2,
    movePattern: MovePattern,
    protected val bombTimePattern: BombTimePattern
) : GameObject(bitmap, radiusPx, velocityPx, positionPx, movePattern) {

    override fun updateState(
        fieldWidth: Int,
        fieldHeight: Int,
        secondsElapsed: Float,
        objectManager: ObjectManager
    ): Boolean {
        super.updateState(fieldWidth, fieldHeight, secondsElapsed, objectManager)
        bombTimePattern.apply(this, secondsElapsed)
        return isTimeExpired()
    }

    open protected fun isTimeExpired(): Boolean {
        return secondsBeforeBlast <= 0
    }
}