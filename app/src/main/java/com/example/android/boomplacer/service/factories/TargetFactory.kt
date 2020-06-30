package com.example.android.boomplacer.service.factories

import android.graphics.Bitmap
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.gamedata.TargetData
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.modifiers.TargetModifiers
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.model.gameobjects.targets.Target
import com.example.android.boomplacer.service.builders.TargetBuilder

class TargetFactory(targetIcon: Bitmap, fieldWidth: Int, fieldHeight: Int) :
    Factory<Target>(targetIcon, fieldWidth, fieldHeight) {

    override fun create(levelDifficulty: LevelDifficulty): List<Target> {
        val targetModifiers = calculateModifiers(levelDifficulty)
        val availableMovePatterns =
            TargetData.AVAILABLE_MOVE_PATTERNS.filter { it.isAvailableInCategory(levelDifficulty.levelCategory) }
        val movePatternsPool = createWeightedPatternsPool(availableMovePatterns)

        val builder = TargetBuilder()
        return builder.apply {
            unscaledBitmap = icon
            score = calculateScore(levelDifficulty)
            angle = randomizeAngle()
            speedDp = TargetData.BASE_SPEED * targetModifiers.speedModifier
            radiusDp = TargetData.BASE_RADIUS * targetModifiers.radiusModifier
            positionPx = Vector2.createRandom(fieldWidth, fieldHeight)
            movePattern = movePatternsPool.getRandom() as MovePattern
        }.build(calculateAmount(levelDifficulty))
    }

    override fun calculateAmount(levelDifficulty: LevelDifficulty): Int =
        TargetData.AMOUNT_FORMULA(levelDifficulty.difficultyValue)

    override fun calculateModifiers(levelDifficulty: LevelDifficulty): TargetModifiers {
        val targetSpeedModifier: Float =
            TargetData.SPEED_MODIFIER_FORMULA(levelDifficulty.difficultyValue)
        val targetRadiusModifier: Float =
            TargetData.RADIUS_MODIFIER_FORMULA(levelDifficulty.difficultyValue)
        return TargetModifiers(targetSpeedModifier, targetRadiusModifier)
    }

    private fun calculateScore(levelDifficulty: LevelDifficulty): Int =
        TargetData.SCORE_FORMULA(levelDifficulty.difficultyValue)
}