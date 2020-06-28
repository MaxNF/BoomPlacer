package com.example.android.boomplacer.model.gameobjects.targets

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.android.boomplacer.R
import com.example.android.boomplacer.model.gameobjects.base.MovePattern
import com.example.android.boomplacer.model.gameobjects.base.Target
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.movepatterns.LinearMovePattern

class LinearTarget(
    image: Bitmap,
    radiusDp: Float,
    speedDp: Float,
    fieldWidth: Int,
    fieldHeight: Int,
    movePattern: MovePattern,
    radiusModifierDp: Float,
    speedModifierDp: Float
) : Target(
    image,
    radiusDp,
    speedDp,
    fieldWidth,
    fieldHeight,
    movePattern,
    radiusModifierDp,
    speedModifierDp
) {
    companion object {
        fun create(
            resources: Resources,
            fieldWidth: Int,
            fieldHeight: Int,
            radiusModifierDp: Float,
            speedModifierDp: Float
        ) = LinearTarget(
            BitmapFactory.decodeResource(resources, R.drawable.ic_target),
            16f,
            100f,
            fieldWidth,
            fieldHeight,
            LinearMovePattern(),
            radiusModifierDp,
            speedModifierDp
        )
    }
}
