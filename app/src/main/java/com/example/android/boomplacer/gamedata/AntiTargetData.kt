package com.example.android.boomplacer.gamedata

import com.example.android.boomplacer.model.gameobjects.movepatterns.LinearMovePattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern

class AntiTargetData {
    companion object {
        const val BASE_SPEED: Float = TargetData.BASE_SPEED
        const val BASE_RADIUS: Float = TargetData.BASE_RADIUS

        val AMOUNT_FORMULA: (difficultyValue: Int) -> Int = TODO()
        val SCORE_FORMULA: (difficultyValue: Int) -> Int = TODO()
        val SPEED_FORMULA: (difficultyValue: Int) -> Float = TODO()
        val RADIUS_FORMULA: (difficultyValue: Int) -> Float = TODO()

        val AVAILABLE_MOVE_PATTERNS: List<MovePattern> =
            listOf(LinearMovePattern(LevelCategory.EASY, 1))
    }
}