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

class BlastFactory(icon: Bitmap?, private val paint: Paint, fieldWidth: Int, fieldHeight: Int) :
    Factory<Blast>(icon, fieldWidth, fieldHeight) {
    override fun create(levelDifficulty: LevelDifficulty): List<Blast> {
        val difficultyValue = levelDifficulty.difficultyValue

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
            radiusDp = BlastData.RADIUS_FORMULA(difficultyValue)
            radiusChangeRate = BlastData.RADIUS_CHANGE_RATE_FORMULA(difficultyValue)
            speedDp = BlastData.SPEED_FORMULA(difficultyValue)
            movePattern = movePatternsPool.getRandom() as MovePattern
            radiusPattern = blastPatternsPool.getRandom() as BlastRadiusPattern
        }.build(BlastData.AMOUNT_FORMULA(difficultyValue))
    }
}