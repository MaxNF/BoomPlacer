package com.example.android.boomplacer.model.gameobjects.movepatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.base.GameObject

abstract class MovePattern(val minLevelCategory: LevelCategory, val patternDifficulty: Int) {
    open fun apply(gameObject: GameObject, secondsElapsed: Float) {
        gameObject.positionPx = calculatePosition(gameObject, secondsElapsed)
    }

    protected abstract fun calculatePosition(gameObject: GameObject, secondsElapsed: Float): Vector2
}