package com.example.android.boomplacer.gameobjects.targets

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.android.boomplacer.R
import com.example.android.boomplacer.gameobjects.MovePattern
import com.example.android.boomplacer.gameobjects.base.Target
import com.example.android.boomplacer.math.Vector2

class LinearTarget(
    image: Bitmap,
    radius: Float,
    velocity: Vector2,
    position: Vector2,
    movePattern: MovePattern
) : Target(image, radius, velocity, position, movePattern) {
    companion object {
        fun create(resources: Resources) = LinearTarget(
            BitmapFactory.decodeResource(resources, R.drawable.ic_star),
            16f,
            Vector2(200f, 200f),
            Vector2.zero(),
            MovePattern.linear
        )
    }
}
