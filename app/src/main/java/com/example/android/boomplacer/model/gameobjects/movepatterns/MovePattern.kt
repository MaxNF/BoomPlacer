package com.example.android.boomplacer.model.gameobjects.movepatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.base.GameObject
import com.example.android.boomplacer.model.gameobjects.base.Pattern

abstract class MovePattern(minLevelCategory: LevelCategory, patternDifficulty: Int) :
    Pattern<GameObject>(minLevelCategory, patternDifficulty)