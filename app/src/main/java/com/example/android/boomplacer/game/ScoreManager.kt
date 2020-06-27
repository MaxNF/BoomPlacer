package com.example.android.boomplacer.game

import com.example.android.boomplacer.model.gameobjects.base.Bomb
import com.example.android.boomplacer.model.gameobjects.base.Target

class ScoreManager() {
    var currentScore: Int = 0
        private set

    fun increaseScore(destroyedTarget: Target) {
        currentScore += destroyedTarget.calculateScore()
    }

    fun increaseScore(bombsLeft: List<Bomb>) {
        bombsLeft.forEach { bomb ->
            currentScore += bomb.calculateScore()
        }
    }

    fun clearScore() {
        currentScore = 0
    }
}