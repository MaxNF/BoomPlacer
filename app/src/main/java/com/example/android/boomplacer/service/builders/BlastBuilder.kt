package com.example.android.boomplacer.service.builders

import android.graphics.Paint
import com.example.android.boomplacer.model.gameobjects.blastpatterns.BlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.blasts.Blast

class BlastBuilder : GameObjectBuilder<Blast>() {

    var paint: Paint? = null
        get() = field ?: throwPropertyNotSetException(::paint.name)

    var radiusPattern: BlastRadiusPattern? = null
        get() = field ?: throwPropertyNotSetException(::radiusPattern.name)

    var radiusChangeRate: Float? = null
    get() = field ?: throwPropertyNotSetException(::radiusChangeRate.name)

    override fun build(): Blast {
        return Blast(
            paint!!,
            radiusPx,
            radiusChangeRate!!,
            velocityPx,
            positionPx!!,
            movePattern!!,
            radiusPattern!!
        )
    }
}