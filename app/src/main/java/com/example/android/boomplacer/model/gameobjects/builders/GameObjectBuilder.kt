package com.example.android.boomplacer.model.gameobjects.builders

import android.graphics.Bitmap
import androidx.core.graphics.scale
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.GameObject
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern

abstract class GameObjectBuilder<T : GameObject> {
    var unscaledBitmap: Bitmap? = null
    protected val scaledBitmap: Bitmap
        get() {
            unscaledBitmap?.let {
                return it.scale((radiusPx * 2).toInt(), (radiusPx * 2).toInt())
            }
            throwPropertyNotSetException(::unscaledBitmap.name)
        }

    var radiusDp: Float? = null
    protected val radiusPx: Float
        get() {
            radiusDp?.let {
                dpToPx(it)
            }
            throwPropertyNotSetException(::radiusDp.name)
        }

    var angle: Float? = null
    var speedDp: Float? = null
    protected val velocityPx: Vector2
        get() {
            angle?.let { angle ->
                speedDp?.let { speedDp ->
                    val speedPx = dpToPx(speedDp)
                    Vector2.create(angle, speedPx)
                }
                throwPropertyNotSetException(::speedDp.name)
            }
            throwPropertyNotSetException(::angle.name)
        }

    var positionPx: Vector2? = null
        get() = field ?: throwPropertyNotSetException(::movePattern.name)

    var movePattern: MovePattern? = null
        get() = field ?: throwPropertyNotSetException(::movePattern.name)


    abstract fun build(): T

    fun build(amount: Int): List<T> {
        val list = mutableListOf<T>()
        for (i: Int in 0 until amount) {
            list.add(build())
        }
        return list
    }

    fun reset() {
        unscaledBitmap = null
        radiusDp = null
        angle = null
        speedDp = null
        positionPx = null
        movePattern = null
    }

    protected fun throwPropertyNotSetException(propertyName: String): Nothing {
        throw PropertyNotSetException("Property $propertyName was not configured.")
    }
}