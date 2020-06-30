package com.example.android.boomplacer.gamedata

import com.example.android.boomplacer.model.gameobjects.movepatterns.LinearMovePattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern

class TargetData {
    companion object {
        const val BASE_SPEED: Float = 100f
        const val BASE_SPEED_MODIFIER: Float = 10f
        const val BASE_RADIUS: Float = 50f
        const val BASE_RADIUS_MODIFIER: Float = 5f

        val AMOUNT_FORMULA: (difficultyValue: Int) -> Int = TODO()
        val SCORE_FORMULA: (difficultyValue: Int) -> Int = TODO()
        val SPEED_MODIFIER_FORMULA: (difficultyValue: Int) -> Float = TODO()
        val RADIUS_MODIFIER_FORMULA: (difficultyValue: Int) -> Float = TODO()

        val AVAILABLE_MOVE_PATTERNS: List<MovePattern> =
            listOf(LinearMovePattern(LevelCategory.EASY, 1))
    }
}