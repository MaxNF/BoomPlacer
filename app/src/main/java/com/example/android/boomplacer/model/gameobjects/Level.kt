package com.example.android.boomplacer.model.gameobjects

import com.example.android.boomplacer.model.gameobjects.bombs.Bomb
import com.example.android.boomplacer.model.gameobjects.targets.Target
import com.example.android.boomplacer.model.gameobjects.factories.BombFactory
import com.example.android.boomplacer.model.gameobjects.factories.TargetFactory

class Level(
    val id: Int,
    fieldWidth: Int,
    fieldHeight: Int
) {
    val targetSpeedModifier: Float = 0.8f + id * 0.2f
    val targetRadiusModifier: Float = 1.1f - id * 0.05f
    val blastRadiusModifier: Float = 1.05f - id * 0.05f
    val blastRadiusDecreaseRateModifier: Float = 0.95f + id * 0.05f

    val targets: List<Target> =
        targetFactory.createLinearTargets(id, fieldWidth, fieldHeight, targetRadiusModifier, targetSpeedModifier)
    val bombs: List<Bomb> = bombFactory.createBombs(id * 2)
}