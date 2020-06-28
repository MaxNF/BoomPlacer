package com.example.android.boomplacer.game

interface GameFlow {
    fun startGame()
    fun stopGame()
    fun pauseGame()
    fun unPauseGame()
    fun isPaused(): Boolean
    fun initNewGame(levelId: Int)
}