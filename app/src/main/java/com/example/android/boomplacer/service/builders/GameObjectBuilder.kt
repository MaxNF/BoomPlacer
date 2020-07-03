package com.example.android.boomplacer.service.builders

import android.graphics.Bitmap
import androidx.core.graphics.scale
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.PropertyNotSetException
import com.example.android.boomplacer.model.gameobjects.base.GameObject
import com.example.android.boomplacer.model.gameobjects.base.Pattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern

abstract class GameObjectBuilder<T : GameObject> : Builder() {
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
                return dpToPx(it)
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
                    return Vector2.create(angle, speedPx)
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

    /**
     * @param changeFunction - function to be invoked on each iteration of creating object.
     * Can be used to change properties of the builder.
     * */
    @Suppress("UNCHECKED_CAST")
    fun <E : GameObjectBuilder<T>> build(amount: Int, changeFunction: E.() -> Unit): List<T> {
        val list = mutableListOf<T>()
        for (i: Int in 0 until amount) {
            changeFunction.invoke(this as E)
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
}