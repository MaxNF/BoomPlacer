package com.example.android.boomplacer.service.builders

import com.example.android.boomplacer.model.gameobjects.targets.Target

class TargetBuilder : GameObjectBuilder<Target>() {
    var score: Int? = null
        get() = field ?: throwPropertyNotSetException(::score.name)

    override fun build(): Target {
        return Target(scaledBitmap, score!!, radiusPx, velocityPx, positionPx!!, movePattern!!)
    }
}