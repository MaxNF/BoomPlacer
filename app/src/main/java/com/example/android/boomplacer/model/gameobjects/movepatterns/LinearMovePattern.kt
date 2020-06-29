package com.example.android.boomplacer.model.gameobjects.movepatterns

import com.example.android.boomplacer.math.Vector2

class LinearMovePattern : MovePattern() {
    override fun calculatePosition(position: Vector2, velocity: Vector2): Vector2 {
        return position + velocity
    }
}