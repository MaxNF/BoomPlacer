package com.example.android.boomplacer.gamedata

import com.example.android.boomplacer.model.gameobjects.bombpatterns.BombTimePattern
import com.example.android.boomplacer.model.gameobjects.bombpatterns.SimpleBombTimePattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.LinearMovePattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.StaticMovePattern

class BombData {
    companion object {
        const val BASE_SPEED: Float = 0f
        const val BASE_RADIUS: Float = 8f
        const val BASE_TIME_SEC: Float = 1f

        val AMOUNT_FORMULA: (difficultyValue: Int) -> Int = { dif ->
            val levelTargetCount = TargetData.AMOUNT_FORMULA(dif)
            val additionalBombs = levelTargetCount - (levelTargetCount * dif / 45f)
            val totalBombs = levelTargetCount + additionalBombs
            if (totalBombs < levelTargetCount) {
                levelTargetCount
            } else {
                totalBombs.toInt()
            }
        }
        val SCORE_FORMULA: (difficultyValue: Int) -> Int = { dif -> 1 + 3 * (dif / 10) }
        val RADIUS_FORMULA: (difficultyValue: Int) -> Float = { BASE_RADIUS }
        val SPEED_FORMULA: (difficultyValue: Int) -> Float =
            { dif -> BASE_SPEED + 1f * (dif / 20f) }
        val TIME_FORMULA: (difficultyValue: Int) -> Float = { dif -> BASE_TIME_SEC + dif / 20f }

        val AVAILABLE_MOVE_PATTERNS: List<MovePattern> =
            listOf(StaticMovePattern(LevelCategory.EASY, 1))
        val AVAILABLE_TIME_PATTERNS: List<BombTimePattern> =
            listOf(SimpleBombTimePattern(LevelCategory.EASY, 1))
    }
}