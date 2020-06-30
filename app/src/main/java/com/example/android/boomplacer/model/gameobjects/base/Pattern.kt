package com.example.android.boomplacer.model.gameobjects.base

import com.example.android.boomplacer.gamedata.LevelCategory

open abstract class Pattern<T : GameObject>(
    val minLevelCategory: LevelCategory,
    val patternDifficulty: Int
) {

    fun isAvailableInCategory(levelCategory: LevelCategory) = levelCategory >= minLevelCategory

    abstract fun applyPattern(gameObject: T, secondsElapsed: Float)
}