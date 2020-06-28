package com.example.android.boomplacer.model.gameobjects.blasts

import android.graphics.Color
import android.graphics.Paint
import com.example.android.boomplacer.model.gameobjects.base.MovePattern
import com.example.android.boomplacer.model.gameobjects.base.Blast
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.movepatterns.StaticMovePattern

class StaticBlast(
    paint: Paint,
    radiusDp: Float,
    radiusDecreaseRateDp: Float,
    velocityDp: Vector2,
    positionPx: Vector2,
    movePattern: MovePattern
) : Blast(paint, radiusDp, radiusDecreaseRateDp, velocityDp, positionPx, movePattern) {
    companion object {
        fun create(): StaticBlast {
            val paint = Paint().apply {
                alpha = 80
                color = Color.RED
            }
            return StaticBlast(
                paint,
                48f,
                64f,
                Vector2.zero(),
                Vector2.zero(),
                StaticMovePattern()
            )
        }
    }
}