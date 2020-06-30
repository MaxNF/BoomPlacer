package com.example.android.boomplacer.model.gameobjects.levels

import com.example.android.boomplacer.model.WeightedBag
import com.example.android.boomplacer.model.gameobjects.bombs.Bomb
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.model.gameobjects.targets.Target

class LevelFactory {
    fun createLevel(levelProperties: LevelProperties): Level {
        val name = createName()
        val targets = createTargets(
            levelProperties.targetsCount,
            levelProperties.targetMovePatternsPool,
            levelProperties.targetSpeedModifier,
            levelProperties.targetRadiusModifier
        )
        val antiTargets = createAntiTargets(
            levelProperties.antiTargetsCount,
            levelProperties.antiTargetMovePatternPool,
            levelProperties.antiTargetSpeedModifier,
            levelProperties.antiTargetRadiusModifier
        )
        val bombs = createBombs(
            levelProperties.bombsCount,
            levelProperties.blastRadiusModifier,
            levelProperties.blastRadiusDecreaseRateModifier
        )
        
    }

    private fun createName(): String {

    }

    private fun createTargets(
        amount: Int,
        movePatterns: WeightedBag<MovePattern>,
        speedModifier: Float,
        radiusModifier: Float
    ): List<Target> {

    }

    private fun createAntiTargets(
        amount: Int,
        movePatterns: WeightedBag<MovePattern>,
        speedModifier: Float,
        radiusModifier: Float
    ): List<Target> {

    }

    private fun createBombs(
        amount: Int,
        blastRadiusModifier: Float,
        blastRadiusDecreaseRateModifier: Float
    ): List<Bomb> {

    }
}