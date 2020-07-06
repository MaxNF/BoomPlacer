package com.example.android.boomplacer.extensions

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import com.example.android.boomplacer.R
import com.example.android.boomplacer.game.Game
import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.gameobjects.levels.Level
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.service.builders.LevelBuilder

fun createDebugLevel(game: Game, levelDifficulty: LevelDifficulty): Level {
    val resources = game.context.resources
    return LevelBuilder(resources).apply {
        fieldWidth = game.width
        fieldHeight = game.height
        this.levelDifficulty = levelDifficulty
    }.build()
}