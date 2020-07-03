package com.example.android.boomplacer.service.builders

import android.graphics.Paint
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.model.gameobjects.blastpatterns.BlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.blasts.Blast

class BlastBuilder : GameObjectBuilder<Blast>() {

    var paint: Paint? = null
        get() = field ?: throwPropertyNotSetException(::paint.name)

    var radiusPattern: BlastRadiusPattern? = null
        get() = field ?: throwPropertyNotSetException(::radiusPattern.name)

    var radiusChangeRateDp: Float? = null
    private val radiusChangeRatePx: Float
        get() {
            radiusChangeRateDp?.let {
                return dpToPx(it)
            }
            throwPropertyNotSetException(::radiusChangeRateDp.name)
        }

    override fun build(): Blast {
        return Blast(
            paint!!,
            radiusPx,
            radiusChangeRatePx,
            velocityPx,
            positionPx!!,
            movePattern!!,
            radiusPattern!!
        )
    }
}