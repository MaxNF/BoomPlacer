package com.example.android.boomplacer.service.factories

import android.graphics.Bitmap
import android.graphics.Paint
import com.example.android.boomplacer.gamedata.BlastData
import com.example.android.boomplacer.gamedata.BombData
import com.example.android.boomplacer.model.gameobjects.blastpatterns.LinearDecreaseBlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.blasts.Blast
import com.example.android.boomplacer.model.gameobjects.bombpatterns.BombTimePattern
import com.example.android.boomplacer.model.gameobjects.bombs.Bomb
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.model.gameobjects.modifiers.BlastModifiers
import com.example.android.boomplacer.model.gameobjects.modifiers.BombModifiers
import com.example.android.boomplacer.service.builders.BlastBuilder
import com.example.android.boomplacer.service.builders.BombBuilder

class BombFactory(bombIcon: Bitmap, blastPaint: Paint, fieldWidth: Int, fieldHeight: Int) :
    Factory<Bomb>(bombIcon, fieldWidth, fieldHeight) {

    override fun create(levelDifficulty: LevelDifficulty): List<Bomb> {
        val bombModifiers = calculateModifiers(levelDifficulty)
        val availableTimePatterns =
            BombData.AVAILABLE_TIME_PATTERNS.filter { it.isAvailableInCategory(levelDifficulty.levelCategory) }
        val timePatternsPool = createWeightedPatternsPool(availableTimePatterns)

        return BombBuilder().apply {
            bombTimePattern = timePatternsPool.getRandom() as BombTimePattern
            score = calculateScore(levelDifficulty)
            secondsBeforeBlast = BombData.BASE_TIME_SEC * bombModifiers.timeModifier
        }.build(calculateAmount(levelDifficulty))
    }

    override fun calculateAmount(levelDifficulty: LevelDifficulty): Int =
        BombData.AMOUNT_FORMULA(levelDifficulty.difficultyValue)

    override fun calculateModifiers(levelDifficulty: LevelDifficulty): BombModifiers {
        val timeModifier: Float = BombData.TIME_MODIFIER_FORMULA(levelDifficulty.difficultyValue)
        return BombModifiers(timeModifier)
    }

    private fun calculateScore(levelDifficulty: LevelDifficulty): Int =
        BombData.SCORE_FORMULA(levelDifficulty.difficultyValue)
}