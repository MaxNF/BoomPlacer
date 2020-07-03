package com.example.android.boomplacer.gamedata

import com.example.android.boomplacer.model.gameobjects.movepatterns.LinearMovePattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern

class AntiTargetData {
    companion object {
        const val BASE_SPEED: Float = TargetData.BASE_SPEED
        const val BASE_RADIUS: Float = TargetData.BASE_RADIUS

        val AMOUNT_FORMULA: (difficultyValue: Int) -> Int = { dif -> dif / 30 + (dif % 30) / 10 }
        val SCORE_FORMULA: (difficultyValue: Int) -> Int = { 0 } // destroying anti target means game over
        val SPEED_FORMULA: (difficultyValue: Int) -> Float =
            { dif -> TargetData.BASE_SPEED + TargetData.BASE_SPEED * (dif / 20f) }
        val RADIUS_FORMULA: (difficultyValue: Int) -> Float =
            { dif -> TargetData.BASE_RADIUS - TargetData.BASE_RADIUS * (dif / 50f) }

        val AVAILABLE_MOVE_PATTERNS: List<MovePattern> =
            listOf(LinearMovePattern(LevelCategory.EASY, 1))
    }
}