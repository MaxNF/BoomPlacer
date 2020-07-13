package com.example.android.boomplacer.model.gameobjects.movepatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.base.GameObject

class StaticMovePattern(
    minLevelCategory: LevelCategory,
    patternDifficulty: Int
) : MovePattern(minLevelCategory, patternDifficulty) {
    override fun applyPattern(gameObject: GameObject, secondsElapsed: Float) {
        super.applyPattern(gameObject, secondsElapsed)
        // do nothing
    }
}