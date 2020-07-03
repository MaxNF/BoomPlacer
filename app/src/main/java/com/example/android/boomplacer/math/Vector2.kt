package com.example.android.boomplacer.math

import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

class Vector2(var x: Float, var y: Float) {
    companion object {
        fun zero() = Vector2(0f, 0f)
        fun create(angle: Float, speed: Float) = Vector2(speed * sin(angle), speed * cos(angle))
        fun create(vector2: Vector2) = Vector2(vector2.x, vector2.y)
        fun createRandom(maxX: Int, maxY: Int) =
            Vector2(Random.nextInt(maxX).toFloat(), Random.nextInt(maxY).toFloat())
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