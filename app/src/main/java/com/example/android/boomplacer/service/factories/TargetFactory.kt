package com.example.android.boomplacer.service.factories

import android.graphics.Bitmap
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.extensions.filterForDifficulty
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.gamedata.TargetData
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.modifiers.TargetModifiers
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.model.gameobjects.targets.Target
import com.example.android.boomplacer.service.builders.TargetBuilder

open class TargetFactory(
    targetIcon: Bitmap,
    protected val fieldWidth: Int,
    protected val fieldHeight: Int
) : Factory<Target>(targetIcon) {

    override fun create(levelDifficulty: LevelDifficulty): List<Target> {
        val difficultyValue = levelDifficulty.difficultyValue
        val availableMovePatterns =
            TargetData.AVAILABLE_MOVE_PATTERNS.filterForDifficulty(levelDifficulty)
        val movePatternsPool = createWeightedPatternsPool(availableMovePatterns)

        val radiusDp = TargetData.RADIUS_FORMULA(difficultyValue)

        return TargetBuilder().apply {
            unscaledBitmap = icon
            score = TargetData.SCORE_FORMULA(difficultyValue)
            speedDp = TargetData.SPEED_FORMULA(difficultyValue)
            this.radiusDp = radiusDp
            movePattern = movePatternsPool.getRandom() as MovePattern
        }.build<TargetBuilder>(TargetData.AMOUNT_FORMULA(difficultyValue)) {
            angle = randomizeAngle()
            positionPx = Vector2.createRandom(
                fieldWidth - dpToPx(radiusDp).toInt(),
                fieldHeight - dpToPx(radiusDp).toInt()
            )
        }
    }
}