package com.example.android.boomplacer.model.gameobjects.builders

import android.graphics.Paint
import com.example.android.boomplacer.model.gameobjects.blastpatterns.BlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.blasts.Blast

class BlastBuilder : GameObjectBuilder<Blast>() {
    var paint: Paint? = null
        get() = field ?: throwPropertyNotSetException(::paint.name)

    var blastRadiusPattern: BlastRadiusPattern? = null
        get() = field ?: throwPropertyNotSetException(::blastRadiusPattern.name)

    override fun build(): Blast {
        return Blast(paint!!, radiusPx, velocityPx, positionPx, movePattern!!, blastRadiusPattern!!)
    }
}