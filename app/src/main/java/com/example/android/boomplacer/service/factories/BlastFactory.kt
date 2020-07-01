package com.example.android.boomplacer.service.factories

import android.graphics.Bitmap
import android.graphics.Paint
import com.example.android.boomplacer.gamedata.BlastData
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.blastpatterns.BlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.blasts.Blast
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.model.gameobjects.modifiers.BlastModifiers
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.service.builders.BlastBuilder

class BlastFactory(icon: Bitmap?, val paint: Paint, fieldWidth: Int, fieldHeight: Int) :
    Factory<Blast>(icon, fieldWidth, fieldHeight) {
    override fun create(levelDifficulty: LevelDifficulty): List<Blast> {
        val blastModifiers = calculateModifiers(levelDifficulty)

        val availableRadiusPatterns =
            BlastData.AVAILABLE_RADIUS_PATTERNS.filter { it.isAvailableInCategory(levelDifficulty.levelCategory) }
        val blastPatternsPool = createWeightedPatternsPool(availableRadiusPatterns)

        val availableMovePatterns =
            BlastData.AVAILABLE_MOVE_PATTERNS.filter { it.isAvailableInCategory(levelDifficulty.levelCategory) }
        val movePatternsPool = createWeightedPatternsPool(availableMovePatterns)

        return BlastBuilder().apply {
            paint = paint
            angle = randomizeAngle()
            positionPx = Vector2.createRandom(fieldWidth, fieldHeight)
            radiusDp = BlastData.BASE_RADIUS * blastModifiers.radiusModifier
            radiusChangeRate =
                BlastData.BASE_RADIUS_CHANGE_RATE * blastModifiers.radiusChangeRateModifier
            speedDp = BlastData.BASE_SPEED * blastModifiers.speedModifier
            movePattern = movePatternsPool.getRandom() as MovePattern
            radiusPattern = blastPatternsPool.getRandom() as BlastRadiusPattern
        }.build(calculateAmount(levelDifficulty))
    }

    override fun calculateAmount(levelDifficulty: LevelDifficulty): Int =
        BlastData.AMOUNT_FORMULA(levelDifficulty.difficultyValue)

    override fun calculateModifiers(levelDifficulty: LevelDifficulty): BlastModifiers {
        val speedModifier = BlastData.SPEED_MODIFIER_FORMULA(levelDifficulty.difficultyValue)
        val blastRadiusModifier = BlastData.RADIUS_MODIFIER_FORMULA(levelDifficulty.difficultyValue)
        val blastRadiusDecreaseModifier =
            BlastData.RADIUS_CHANGE_RATE_MODIFIER_FORMULA(levelDifficulty.difficultyValue)
        return BlastModifiers(speedModifier, blastRadiusModifier, blastRadiusDecreaseModifier)
    }
}