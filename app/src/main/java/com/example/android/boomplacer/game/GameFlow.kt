package com.example.android.boomplacer.game

import com.example.android.boomplacer.model.gameobjects.levels.Level

interface GameFlow {
    fun startGame()
    fun stopGame()
    fun pauseGame()
    fun unPauseGame()
    fun isPaused(): Boolean
    fun initNewGame(level: Level)
}