package com.example.android.boomplacer.model.gameobjects

import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.base.Bomb
import com.example.android.boomplacer.model.gameobjects.base.Target
import com.example.android.boomplacer.model.gameobjects.factories.BombFactory
import com.example.android.boomplacer.model.gameobjects.factories.TargetFactory
import com.example.android.boomplacer.model.gameobjects.targets.LinearTarget
import kotlin.random.Random

class Level(
    val id: Int,
    targetFactory: TargetFactory,
    bombFactory: BombFactory,
    fieldWidth: Int,
    fieldHeight: Int
) {
    val targetSpeedModifier: Float = 0.8f + id * 0.2f
    val targetRadiusModifier: Float = 1.1f - id * 0.05f
    val blastRadiusModifier: Float = 1.05f - id * 0.05f
    val blastRadiusDecreaseRateModifier: Float = 0.95f + id * 0.05f

    val targets: List<Target> =
        targetFactory.createLinearTargets(id, fieldWidth, fieldHeight, targetRadiusModifier, targetSpeedModifier)
    val bombs: List<Bomb> = bombFactory.createStaticBombs(id * 2)
}