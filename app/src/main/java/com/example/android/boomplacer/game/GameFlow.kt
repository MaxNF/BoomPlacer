package com.example.android.boomplacer.game

import com.example.android.boomplacer.model.gameobjects.base.Bomb
import com.example.android.boomplacer.model.gameobjects.base.Target

interface GameFlow {
    fun startGame()
    fun stopGame()
    fun pauseGame()
    fun unPauseGame()
    fun isPaused(): Boolean
    fun initNewGame(targets: List<Target>, bombs: List<Bomb>)
}