package com.example.android.boomplacer.model.gameobjects.factories

import android.content.res.Resources
import android.graphics.Bitmap
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.gamedata.TargetData
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.WeightedBag
import com.example.android.boomplacer.model.gameobjects.modifiers.TargetModifiers
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.model.gameobjects.targets.Target
import com.example.android.boomplacer.model.gameobjects.targets.TargetBuilder
import kotlin.random.Random

class TargetFactory(
    private val targetIcon: Bitmap,
    private val fieldWidth: Int,
    private val fieldHeight: Int
) {

    private fun createTargets(levelDifficulty: LevelDifficulty): List<Target> {
        val builder = TargetBuilder()
        val movePatternsPool = createMovePatternsPool(levelDifficulty)
        val targetModifiers = calculateTargetModifiers(levelDifficulty)

        return builder.apply {
            unscaledBitmap = targetIcon
            score = calculateScore(levelDifficulty)
            angle = randomizeAngle()
            speedDp = TargetData.BASE_SPEED * targetModifiers.targetSpeedModifier
            radiusDp = TargetData.BASE_RADIUS * targetModifiers.targetSpeedModifier
            positionPx = Vector2.createRandom(fieldWidth, fieldHeight)
            movePattern = movePatternsPool.getRandom()
        }.build(calculateAmount(levelDifficulty))
    }

    private fun calculateAmount(levelDifficulty: LevelDifficulty): Int =
        TargetData.AMOUNT_FORMULA(levelDifficulty.difficultyValue)

    private fun calculateTargetModifiers(levelDifficulty: LevelDifficulty): TargetModifiers {
        val targetSpeedModifier: Float =
            TargetData.SPEED_MODIFIER_FORMULA(levelDifficulty.difficultyValue)
        val targetRadiusModifier: Float =
            TargetData.RADIUS_MODIFIER_FORMULA(levelDifficulty.difficultyValue)
        return TargetModifiers(targetSpeedModifier, targetRadiusModifier)
    }

    private fun createMovePatternsPool(levelDifficulty: LevelDifficulty): WeightedBag<MovePattern> {
        val weightedBag = WeightedBag<MovePattern>()
        val patternsPool =
            TargetData.AVAILABLE_MOVE_PATTERNS.filter { levelDifficulty.levelCategory >= it.minLevelCategory }
        val accumulatedDifficulty = patternsPool.sumBy { it.patternDifficulty }
        patternsPool.forEach {
            weightedBag.add(it, accumulatedDifficulty / it.patternDifficulty)
        }
        return weightedBag
    }

    private fun calculateScore(levelDifficulty: LevelDifficulty): Int =
        TargetData.SCORE_FORMULA(levelDifficulty.difficultyValue)

    private fun randomizeAngle(): Float = Random.nextFloat() * 360
}