package com.example.android.boomplacer.model.gameobjects.blasts

import android.graphics.Color
import android.graphics.Paint
import com.example.android.boomplacer.model.gameobjects.base.MovePattern
import com.example.android.boomplacer.model.gameobjects.base.Blast
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.movepatterns.StaticMovePattern

class StaticBlast(
    paint: Paint,
    radius: Float,
    radiusDecreaseRate: Float,
    velocity: Vector2,
    position: Vector2,
    movePattern: MovePattern
) : Blast(paint, radius, radiusDecreaseRate, velocity, position, movePattern) {
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