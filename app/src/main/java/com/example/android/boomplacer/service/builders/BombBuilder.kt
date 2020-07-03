package com.example.android.boomplacer.service.builders

import com.example.android.boomplacer.model.gameobjects.blasts.Blast
import com.example.android.boomplacer.model.gameobjects.bombpatterns.BombTimePattern
import com.example.android.boomplacer.model.gameobjects.bombs.Bomb

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
            blast!!.getCopy(), // we do not want a single blast with his own state be shared among all bombs
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