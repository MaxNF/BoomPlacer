package com.example.android.boomplacer.model.gameobjects.levels

import com.example.android.boomplacer.model.WeightedBag
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern

class LevelProperties(
    val levelDifficulty: LevelDifficulty,
    val difficultyModifier: Int,
    val targetsCount: Int,
    val antiTargetsCount: Int,
    val bombsCount: Int,
    val targetMovePatternsPool: WeightedBag<MovePattern>,
    val targetSpeedModifier: Float,
    val targetRadiusModifier: Float,
    val antiTargetMovePatternPool: WeightedBag<MovePattern>,
    val antiTargetSpeedModifier: Float,
    val antiTargetRadiusModifier: Float,
    val blastRadiusModifier: Float,
    val blastRadiusDecreaseRateModifier: Float
)