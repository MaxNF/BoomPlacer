package com.example.android.boomplacer.model.gameobjects.movepatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.gameobjects.base.GameObject
import kotlin.math.cos
import kotlin.math.sin

class CurveMovePattern(
    minLevelCategory: LevelCategory,
    patternDifficulty: Int,
    private val angleRadPerSec: Float // 0.5f is ok
) : MovePattern(minLevelCategory, patternDifficulty) {
    override fun applyPattern(gameObject: GameObject, secondsElapsed: Float) {
        val angle = angleRadPerSec * secondsElapsed
        gameObject.velocityPx.rotate(angle)
        gameObject.positionPx = gameObject.positionPx + gameObject.velocityPx * secondsElapsed
    }
}