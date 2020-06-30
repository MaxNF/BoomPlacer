package com.example.android.boomplacer.model.gameobjects.levels

import com.example.android.boomplacer.model.gameobjects.bombs.Bomb
import com.example.android.boomplacer.model.gameobjects.targets.Target

class Level(
    val name: String,
    val targets: List<Target>,
    val antiTargets: List<Target>,
    val bombs: List<Bomb>
)

