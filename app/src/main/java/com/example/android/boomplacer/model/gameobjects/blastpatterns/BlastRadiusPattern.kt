package com.example.android.boomplacer.model.gameobjects.blastpatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.gameobjects.base.Pattern
import com.example.android.boomplacer.model.gameobjects.blasts.Blast

abstract class BlastRadiusPattern(
    minLevelCategory: LevelCategory,
    patternDifficulty: Int
) : Pattern<Blast>(minLevelCategory, patternDifficulty)