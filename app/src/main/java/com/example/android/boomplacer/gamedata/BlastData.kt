package com.example.android.boomplacer.gamedata

import com.example.android.boomplacer.model.gameobjects.blastpatterns.BlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.blastpatterns.LinearDecreaseBlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.model.gameobjects.movepatterns.StaticMovePattern

class BlastData {
    companion object {
        const val BASE_SPEED: Float = 0f
        const val BASE_RADIUS: Float = 50f

        val AMOUNT_FORMULA: (difficultyValue: Int) -> Int = { 1 } // single blast type per level
        val SPEED_FORMULA: (difficultyValue: Int) -> Float =
            { BASE_SPEED } // static blasts, for now...
        val RADIUS_FORMULA: (difficultyValue: Int) -> Float =
            { dif -> BASE_RADIUS - BASE_RADIUS * (dif / 100f) }
        val RADIUS_CHANGE_RATE_FORMULA: (difficultyValue: Int) -> Float =
            { dif ->
                val radiusDp = RADIUS_FORMULA(dif)
                -(radiusDp / 2 + radiusDp / 2 * (dif / 10f))
            }

        val AVAILABLE_MOVE_PATTERNS: List<MovePattern> =
            listOf(StaticMovePattern(LevelCategory.EASY, 1))
        val AVAILABLE_RADIUS_PATTERNS: List<BlastRadiusPattern> =
            listOf(LinearDecreaseBlastRadiusPattern(LevelCategory.EASY, 1))
    }
}