package com.example.android.boomplacer.gamedata

import com.example.android.boomplacer.R
import com.example.android.boomplacer.model.gameobjects.movepatterns.*

class TargetData {
    companion object {
        const val ICON_DRAWABLE_ID = R.drawable.ic_target

        const val BASE_SPEED: Float = 100f
        const val BASE_RADIUS: Float = 20f

        val AMOUNT_FORMULA: (difficultyValue: Int) -> Int = { dif -> 1 + dif / 10 + (dif % 10) / 2 }
        val SCORE_FORMULA: (difficultyValue: Int) -> Int = { dif -> 1 + dif / 10 + (dif % 10) / 4 }
        val SPEED_FORMULA: (difficultyValue: Int) -> Float =
            { dif -> BASE_SPEED + BASE_SPEED * (dif / 20f) }
        val RADIUS_FORMULA: (difficultyValue: Int) -> Float =
            { dif -> BASE_RADIUS - BASE_RADIUS * (dif / 100f) }

        val AVAILABLE_MOVE_PATTERNS: List<MovePattern>
            get() =
                listOf(
                    LinearMovePattern(LevelCategory.EASY, 1),
                    CurveMovePattern(LevelCategory.EASY, 1, 0.5f),
                    ZigZagMovePattern(LevelCategory.EASY, 1, 0.2f),
                    SlowFastMovePattern(LevelCategory.EASY, 1, 5f, 1f)
                )

//                listOf(
//                    LinearMovePattern(LevelCategory.EASY, 1).apply {
//                        combine(
//                            CurveMovePattern(LevelCategory.EASY, 1, 0.5f),
//                            ZigZagMovePattern(LevelCategory.EASY, 1, 0.1f),
//                            SlowFastMovePattern(LevelCategory.EASY, 1, 1f, 1f)
//                        )
//                    })
    }
}