package com.example.android.boomplacer.model.gameobjects.blastpatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.gameobjects.blasts.Blast

class LinearDecreaseBlastRadiusPattern(minLevelCategory: LevelCategory, patternDifficulty: Int) :
    BlastRadiusPattern(minLevelCategory, patternDifficulty) {
    override fun applyPattern(gameObject: Blast, secondsElapsed: Float) {
        gameObject.radiusPx += gameObject.radiusChangeRate * secondsElapsed
    }
}