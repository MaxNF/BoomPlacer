package com.example.android.boomplacer.model.gameobjects.levels

import com.example.android.boomplacer.gamedata.LevelCategory

class LevelDifficulty(val levelCategory: LevelCategory, val difficultyOffset: Int) {
    val difficultyValue = levelCategory.baseDifficulty + difficultyOffset
}