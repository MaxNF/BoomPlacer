package com.example.android.boomplacer.gameobjects.factories

import android.content.res.Resources
import com.example.android.boomplacer.gameobjects.base.Target
import com.example.android.boomplacer.gameobjects.targets.LinearTarget

class TargetFactory(private val resources: Resources) {
    fun createLinearTargets(amount: Int): List<Target> {
        val list = mutableListOf<Target>()
        for (i: Int in 0 until amount) {
            list.add(LinearTarget.create(resources))
        }
        return list
    }
}