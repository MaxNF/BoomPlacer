package com.example.android.boomplacer.game

import com.example.android.boomplacer.model.gameobjects.levels.Level
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty

interface GameFlow {
    fun startGame()
    fun stopGame()
    fun pauseGame()
    fun unPauseGame()
    fun isPaused(): Boolean
    fun initNewGameAndStart(levelDifficulty: LevelDifficulty)
    fun initAndStartNextLevel()
}