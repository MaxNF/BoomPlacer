package com.example.android.boomplacer.model.gameobjects.bombpatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.gameobjects.base.Pattern
import com.example.android.boomplacer.model.gameobjects.bombs.Bomb

abstract class BombTimePattern(
    minLevelCategory: LevelCategory,
    patternDifficulty: Int
) : Pattern<Bomb>(minLevelCategory, patternDifficulty)