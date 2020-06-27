package com.example.android.boomplacer.math

import kotlin.math.pow
import kotlin.math.sqrt

class Vector2(var x: Float, var y: Float) {
    companion object {
        fun zero() = Vector2(0f, 0f)
    }

    operator fun plus(vector2: Vector2): Vector2 {
        return Vector2(x + vector2.x, y + vector2.y)
    }

    operator fun minus(vector2: Vector2): Vector2 {
        return Vector2(x - vector2.x, y - vector2.y)
    }

    operator fun times(value: Float): Vector2 {
        return Vector2(x * value, y * value)
    }

    fun distanceTo(vector2: Vector2): Float {
        return sqrt((x - vector2.x).pow(2) + (y - vector2.y).pow(2))
    }
}