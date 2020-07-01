package com.example.android.boomplacer.gamedata

import com.example.android.boomplacer.model.gameobjects.blastpatterns.BlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.blastpatterns.LinearDecreaseBlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.StaticMovePattern

class BlastData {
    companion object {
        const val BASE_SPEED: Float = 0f
        const val BASE_RADIUS: Float = 50f
        const val BASE_RADIUS_CHANGE_RATE = 0f

        val RADIUS_MODIFIER_FORMULA: (difficultyValue: Int) -> Float = TODO()
        val RADIUS_CHANGE_RATE_MODIFIER_FORMULA: (difficultyValue: Int) -> Float = TODO()
        val SPEED_MODIFIER_FORMULA: (difficultyValue: Int) -> Float = TODO()
        val AMOUNT_FORMULA: (difficultyValue: Int) -> Int = { 1 } // single blast type per level

        val AVAILABLE_MOVE_PATTERNS: List<MovePattern> =
            listOf(StaticMovePattern(LevelCategory.EASY, 0))
        val AVAILABLE_RADIUS_PATTERNS: List<BlastRadiusPattern> =
            listOf(LinearDecreaseBlastRadiusPattern(LevelCategory.EASY, 1))
    }
}