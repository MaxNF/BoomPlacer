package com.example.android.boomplacer.model.gameobjects.levels

import com.example.android.boomplacer.model.WeightedBag
import com.example.android.boomplacer.model.gameobjects.bombs.Bomb
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import com.example.android.boomplacer.model.gameobjects.targets.Target
import com.example.android.boomplacer.service.factories.TargetFactory

class Level(
    val name: String,
    val targets: List<Target>,
    val antiTargets: List<Target>,
    val bombs: List<Bomb>
)
