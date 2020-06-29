package com.example.android.boomplacer.model.gameobjects.bombpatterns

import com.example.android.boomplacer.model.gameobjects.bombs.Bomb

abstract class BombTimePattern {
    open fun apply(bomb: Bomb, secondsElapsed: Float) {
        bomb.secondsBeforeBlast = calculateTime(bomb, secondsElapsed)
    }

    protected abstract fun calculateTime(bomb: Bomb, secondsElapsed: Float): Float
}