package com.example.android.boomplacer.model.gameobjects.factories

import android.content.res.Resources
import android.graphics.Bitmap
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.model.gameobjects.blasts.Blast
import com.example.android.boomplacer.model.gameobjects.bombpatterns.BombTimePattern
import com.example.android.boomplacer.model.gameobjects.bombs.Bomb
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern

class BombFactory(private val resources: Resources) : Factory() {

    fun createBombs(
        amount: Int,
        icon: Bitmap,
        blast: Blast,
        secondsBeforeBlast: Float,
        radiusDp: Float,
        movePattern: MovePattern,
        bombTimePattern: BombTimePattern
    ): List<Bomb> {
        val list = mutableListOf<Bomb>()
        val radiusPx = dpToPx(radiusDp)
        val scaledIcon = bitmapToScaledBitmap(icon, )
        for (i: Int in 0 until amount) {
            list.add(Bomb)
        }
        return list
    }
}