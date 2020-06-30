package com.example.android.boomplacer.model.gameobjects.movepatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.base.GameObject

class LinearMovePattern(
    minLevelCategory: LevelCategory,
    patternDifficulty: Int
) : MovePattern(minLevelCategory, patternDifficulty) {
    override fun calculatePosition(gameObject: GameObject, secondsElapsed: Float): Vector2 {
        return gameObject.positionPx + gameObject.velocityPx
    }
}