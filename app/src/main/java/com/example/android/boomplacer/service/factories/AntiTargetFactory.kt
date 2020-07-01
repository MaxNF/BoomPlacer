package com.example.android.boomplacer.service.factories

import android.graphics.Bitmap
import com.example.android.boomplacer.extensions.filterForDifficulty
import com.example.android.boomplacer.gamedata.AntiTargetData
import com.example.android.boomplacer.gamedata.TargetData
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.model.gameobjects.targets.Target
import com.example.android.boomplacer.service.builders.TargetBuilder

class AntiTargetFactory(targetIcon: Bitmap, fieldWidth: Int, fieldHeight: Int) : TargetFactory(
    targetIcon,
    fieldWidth,
    fieldHeight
) {

    override fun create(levelDifficulty: LevelDifficulty): List<Target> {
        val difficultyValue = levelDifficulty.difficultyValue
        val availableMovePatterns =
            TargetData.AVAILABLE_MOVE_PATTERNS.filterForDifficulty(levelDifficulty)
        val movePatternsPool = createWeightedPatternsPool(availableMovePatterns)

        return TargetBuilder().apply {
            unscaledBitmap = icon
            score = AntiTargetData.SCORE_FORMULA(difficultyValue)
            angle = randomizeAngle()
            speedDp = AntiTargetData.SPEED_FORMULA(difficultyValue)
            radiusDp = AntiTargetData.RADIUS_FORMULA(difficultyValue)
            positionPx = Vector2.createRandom(fieldWidth, fieldHeight)
            movePattern = movePatternsPool.getRandom() as MovePattern
        }.build(AntiTargetData.AMOUNT_FORMULA(difficultyValue))
    }
}