package com.example.android.boomplacer.model.gameobjects.base

import com.example.android.boomplacer.gamedata.LevelCategory
import java.lang.IllegalArgumentException

abstract class Pattern<T : GameObject>(
    val minLevelCategory: LevelCategory,
    patternDifficulty: Int
) {
    val patternDifficulty: Int = if (patternDifficulty <= 0) {
        throw IllegalArgumentException("Pattern difficulty should be greater than zero.")
    } else {
        patternDifficulty
    }

    fun isAvailableInCategory(levelCategory: LevelCategory) =
        levelCategory >= minLevelCategory

    abstract fun applyPattern(gameObject: T, secondsElapsed: Float)
}