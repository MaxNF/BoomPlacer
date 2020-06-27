package com.example.android.boomplacer.model.gameobjects.factories

import android.content.res.Resources
import com.example.android.boomplacer.model.gameobjects.base.Bomb
import com.example.android.boomplacer.model.gameobjects.bombs.StaticBomb

class BombFactory(private val resources: Resources) {

    fun createStaticBombs(amount: Int): List<Bomb> {
        val list = mutableListOf<Bomb>()
        for (i: Int in 0 until amount) {
            list.add(StaticBomb.create(resources))
        }
        return list
    }
}