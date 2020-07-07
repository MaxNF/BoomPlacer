package com.example.android.boomplacer.math

import kotlin.math.*
import kotlin.random.Random

class Vector2(var x: Float, var y: Float) {
    companion object {
        fun zero() = Vector2(0f, 0f)
        fun create(angleRad: Float, speed: Float) = Vector2(speed * sin(angleRad), speed * cos(angleRad))
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

    fun getMagnitude(): Float {
        return sqrt(x.pow(2) + y.pow(2))
    }

    fun getAngle(): Float {
        return atan(y / x)
    }

    fun rotate(angleRad: Float) {
        val nx = x * cos(angleRad) - y * sin(angleRad)
        val ny = x * sin(angleRad) + y * cos(angleRad)
        x = nx
        y = ny
    }
}