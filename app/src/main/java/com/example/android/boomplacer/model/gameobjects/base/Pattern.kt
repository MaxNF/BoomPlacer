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

    private val subPatterns = mutableListOf<Pattern<T>>()

    fun isAvailableInCategory(levelCategory: LevelCategory) =
        levelCategory >= minLevelCategory

    /**
     * Добавляет к паттерну поведение других паттернов.
     * */
    fun combine(vararg patterns: Pattern<T>) {
        subPatterns.addAll(patterns)
    }

    /**
     * Применяет паттерн к объекту. Реализация по умолчанию применяет добавленные с помощью метода
     * combine() паттерны в порядке их добавления.
     * */
    open fun applyPattern(gameObject: T, secondsElapsed: Float) {
        subPatterns.forEach {
            it.applyPattern(gameObject, secondsElapsed)
        }
    }
}