package com.example.android.boomplacer.model.gameobjects.bombpatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.gameobjects.bombs.Bomb

class SimpleBombTimePattern(minLevelCategory: LevelCategory,
                            patternDifficulty: Int
) : BombTimePattern(minLevelCategory, patternDifficulty){

    override fun applyPattern(gameObject: Bomb, secondsElapsed: Float) {

    }
}