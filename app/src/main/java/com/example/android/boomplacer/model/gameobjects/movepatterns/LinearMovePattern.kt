package com.example.android.boomplacer.model.gameobjects.movepatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.gameobjects.base.GameObject

class LinearMovePattern(
    minLevelCategory: LevelCategory,
    patternDifficulty: Int
) : MovePattern(minLevelCategory, patternDifficulty) {
    override fun applyPattern(gameObject: GameObject, secondsElapsed: Float) {
        super.applyPattern(gameObject, secondsElapsed)
        gameObject.positionPx = gameObject.positionPx + gameObject.velocityPx * secondsElapsed
    }
}