package com.example.android.boomplacer.gameobjects

import com.example.android.boomplacer.math.Vector2

class MovePattern(private val function: (position: Vector2, velocity: Vector2) -> Vector2) {
    companion object {
        val linear: MovePattern = MovePattern { position, velocity ->
            position + velocity
        }

        val static: MovePattern = MovePattern { position, _ ->
            position
        }
    }

    fun calculatePosition(position: Vector2, velocity: Vector2): Vector2 {
        return function(position, velocity)
    }
}