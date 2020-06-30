package com.example.android.boomplacer.model.gameobjects.bombs

import com.example.android.boomplacer.model.gameobjects.blasts.Blast
import com.example.android.boomplacer.model.gameobjects.bombpatterns.BombTimePattern
import com.example.android.boomplacer.model.gameobjects.base.GameObjectBuilder

class BombBuilder : GameObjectBuilder<Bomb>() {
    var blast: Blast? = null
        get() = field ?: throwPropertyNotSetException(::movePattern.name)

    var secondsBeforeBlast: Float? = null
        get() = field ?: throwPropertyNotSetException(::movePattern.name)

    var score: Int? = null
        get() = field ?: throwPropertyNotSetException(::movePattern.name)

    var bombTimePattern: BombTimePattern? = null
        get() = field ?: throwPropertyNotSetException(::movePattern.name)

    override fun build(): Bomb {
        return Bomb(
            scaledBitmap,
            blast!!,
            secondsBeforeBlast!!,
            score!!,
            radiusPx,
            velocityPx,
            positionPx!!,
            movePattern!!,
            bombTimePattern!!
        )
    }
}