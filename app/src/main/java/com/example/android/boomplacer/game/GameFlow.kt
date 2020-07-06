package com.example.android.boomplacer.game

import com.example.android.boomplacer.gamedata.LevelCategory

interface GameFlow {
    val gameEvents: GameEvents
    fun pauseGame()
    fun unPauseGame()
    fun initNewGameAndStart(levelCategory: LevelCategory)
    fun initAndStartNextLevel()
}