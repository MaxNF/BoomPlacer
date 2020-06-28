package com.example.android.boomplacer.model.gameobjects.base

import com.example.android.boomplacer.math.Vector2

abstract class MovePattern {
    abstract fun calculatePosition(position: Vector2, velocity: Vector2): Vector2
}