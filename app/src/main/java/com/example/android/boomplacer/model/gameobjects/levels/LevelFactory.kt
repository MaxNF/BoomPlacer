package com.example.android.boomplacer.model.gameobjects.levels

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.WeightedBag
import com.example.android.boomplacer.model.gameobjects.bombs.Bomb
import com.example.android.boomplacer.model.gameobjects.factories.BombFactory
import com.example.android.boomplacer.model.gameobjects.factories.TargetFactory
import com.example.android.boomplacer.model.gameobjects.modifiers.TargetModifiers
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.model.gameobjects.targets.Target

class LevelFactory(private val targetFactory: TargetFactory, private val bombFactory: BombFactory) {
    fun createLevel(levelProperties: LevelProperties): Level {
        val name = createName()
        val targets = createTargets(
            levelProperties.targetsCount, createTargetModifier()
            val antiTargets =
        createAntiTargets(levelProperties.antiTargetsCount, createAntiTargetModifier())
        val bombs = createBombs(levelProperties.bombsCount, createBlastModifier())

    }

    private fun createName(): String {

    }



    private fun createAntiTargetModifier(
        levelCategory: LevelCategory,
        difficultyModifier: Int
    ): TargetModifiers {

    }

    private fun createAntiTargets(
        amount: Int,
        movePatterns: WeightedBag<MovePattern>,
        speedModifier: Float,
        radiusModifier: Float
    ): List<Target> {

    }

    private fun createBlastModifier(
        levelCategory: LevelCategory,
        difficultyModifier: Int
    ): TargetModifiers {

    }

    private fun createBombs(
        amount: Int,
        blastRadiusModifier: Float,
        blastRadiusDecreaseRateModifier: Float
    ): List<Bomb> {

    }
}