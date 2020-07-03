package com.example.android.boomplacer.service.factories

import android.graphics.Bitmap
import android.graphics.Paint
import com.example.android.boomplacer.extensions.filterForDifficulty
import com.example.android.boomplacer.gamedata.BlastData
import com.example.android.boomplacer.gamedata.BombData
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.blastpatterns.LinearDecreaseBlastRadiusPattern
import com.example.android.boomplacer.model.gameobjects.blasts.Blast
import com.example.android.boomplacer.model.gameobjects.bombpatterns.BombTimePattern
import com.example.android.boomplacer.model.gameobjects.bombs.Bomb
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.model.gameobjects.modifiers.BlastModifiers
import com.example.android.boomplacer.model.gameobjects.modifiers.BombModifiers
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.service.builders.BlastBuilder
import com.example.android.boomplacer.service.builders.BombBuilder

class BombFactory(private val blastFactory: BlastFactory, bombIcon: Bitmap) :
    Factory<Bomb>(bombIcon) {

    override fun create(levelDifficulty: LevelDifficulty): List<Bomb> {
        val bombBlast = blastFactory.create(levelDifficulty)[0]
        val difficultyValue = levelDifficulty.difficultyValue

        val availableMovePatterns =
            BombData.AVAILABLE_MOVE_PATTERNS.filterForDifficulty(levelDifficulty)
        val movePatternsPool = createWeightedPatternsPool(availableMovePatterns)

        val availableTimePatterns =
            BombData.AVAILABLE_TIME_PATTERNS.filterForDifficulty(levelDifficulty)
        val timePatternsPool = createWeightedPatternsPool(availableTimePatterns)

        return BombBuilder().apply {
            unscaledBitmap = icon
            blast = bombBlast
            speedDp = BombData.SPEED_FORMULA(difficultyValue)
            radiusDp = BombData.RADIUS_FORMULA(difficultyValue)
            positionPx = Vector2.zero()
            score = BombData.SCORE_FORMULA(difficultyValue)
            secondsBeforeBlast = BombData.TIME_FORMULA(difficultyValue)
            movePattern = movePatternsPool.getRandom() as MovePattern
            bombTimePattern = timePatternsPool.getRandom() as BombTimePattern
        }.build<BombBuilder>(BombData.AMOUNT_FORMULA(difficultyValue)) {
            angle = randomizeAngle()
        }
    }
}