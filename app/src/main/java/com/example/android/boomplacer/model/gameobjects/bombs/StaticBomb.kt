package com.example.android.boomplacer.model.gameobjects.bombs

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.android.boomplacer.R
import com.example.android.boomplacer.model.gameobjects.base.MovePattern
import com.example.android.boomplacer.model.gameobjects.base.Blast
import com.example.android.boomplacer.model.gameobjects.base.Bomb
import com.example.android.boomplacer.model.gameobjects.blasts.StaticBlast
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.movepatterns.StaticMovePattern

class StaticBomb(
    image: Bitmap,
    blast: Blast,
    timeBeforeBlast: Float,
    radius: Float,
    velocity: Vector2,
    position: Vector2,
    movePattern: MovePattern
) : Bomb(image, blast, timeBeforeBlast, radius, velocity, position, movePattern) {
    companion object {
        fun create(resources: Resources) = StaticBomb(
            BitmapFactory.decodeResource(resources, R.drawable.ic_bomb),
            StaticBlast.create(),
            0.5f,
            8f,
            Vector2.zero(),
            Vector2.zero(),
            StaticMovePattern()
        )
    }
}