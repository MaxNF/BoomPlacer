package com.example.android.boomplacer.model.gameobjects.movepatterns

import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.base.MovePattern

class LinearMovePattern : MovePattern() {
    override fun calculatePosition(position: Vector2, velocity: Vector2): Vector2 {
        return position + velocity
    }
}