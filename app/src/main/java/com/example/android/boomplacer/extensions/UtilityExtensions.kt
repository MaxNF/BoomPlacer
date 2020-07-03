package com.example.android.boomplacer.extensions

import android.content.res.Resources
import com.example.android.boomplacer.model.gameobjects.base.GameObject
import com.example.android.boomplacer.model.gameobjects.base.Pattern
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty

fun <T : GameObject> List<Pattern<T>>.filterForDifficulty(levelDifficulty: LevelDifficulty): List<Pattern<T>> =
    this.filter { levelDifficulty.levelCategory >= it.minLevelCategory }

fun dpToPx(dp: Float): Float {
    return (dp * Resources.getSystem().displayMetrics.density)
}

fun pxToDp(px: Int): Float {
    return px / Resources.getSystem().displayMetrics.density
}