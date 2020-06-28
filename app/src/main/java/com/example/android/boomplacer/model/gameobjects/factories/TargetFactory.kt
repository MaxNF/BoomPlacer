package com.example.android.boomplacer.model.gameobjects.factories

import android.content.res.Resources
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.base.Target
import com.example.android.boomplacer.model.gameobjects.targets.LinearTarget

class TargetFactory(private val resources: Resources) {
    fun createLinearTargets(amount: Int, fieldWidth: Int, fieldHeight: Int, radiusModifier: Float, speedModifier: Float): List<Target> {
        val list = mutableListOf<Target>()
        for (i: Int in 0 until amount) {
            list.add(LinearTarget.create(resources, fieldWidth, fieldHeight, radiusModifier, speedModifier))
        }
        return list
    }
}