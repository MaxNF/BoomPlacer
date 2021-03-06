package com.example.android.boomplacer.service.factories

import android.graphics.Bitmap
import com.example.android.boomplacer.model.WeightedBag
import com.example.android.boomplacer.model.gameobjects.base.GameObject
import com.example.android.boomplacer.model.gameobjects.base.Pattern
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.model.gameobjects.modifiers.Modifiers
import kotlin.random.Random

abstract class Factory<T : GameObject>(protected val icon: Bitmap?) {

    abstract fun create(levelDifficulty: LevelDifficulty): List<T>

    protected open fun randomizeAngle(): Float = Random.nextFloat() * 360

    protected open fun createWeightedPatternsPool(availablePatterns: List<Pattern<in T>>): WeightedBag<Pattern<in T>> {
        val weightedBag = WeightedBag<Pattern<in T>>()
        val accumulatedDifficulty = availablePatterns.sumBy { it.patternDifficulty }
        availablePatterns.forEach {
            weightedBag.add(it, calculatePatternWeight(accumulatedDifficulty, it.patternDifficulty))
        }
        return weightedBag
    }

    protected open fun calculatePatternWeight(
        accumulatedDifficulty: Int,
        patternDifficulty: Int
    ): Int {
        return if (patternDifficulty == 0) {
            1
        } else {
            accumulatedDifficulty / patternDifficulty
        }
    }
}