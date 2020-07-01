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

        val AMOUNT_FORMULA: (difficultyValue: Int) -> Int = TODO()
        val SCORE_FORMULA: (difficultyValue: Int) -> Int = TODO()
        val RADIUS_FORMULA: (difficultyValue: Int) -> Float = TODO()
        val SPEED_FORMULA: (difficultyValue: Int) -> Float = TODO()
        val TIME_FORMULA: (difficultyValue: Int) -> Float = TODO()

        val AVAILABLE_MOVE_PATTERNS: List<MovePattern> =
            listOf(StaticMovePattern(LevelCategory.EASY, 0))
        val AVAILABLE_TIME_PATTERNS: List<BombTimePattern> =
            listOf(SimpleBombTimePattern(LevelCategory.EASY, 0))
    }
}