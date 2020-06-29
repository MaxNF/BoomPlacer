package com.example.android.boomplacer.model.gameobjects.blastpatterns

import com.example.android.boomplacer.model.gameobjects.blasts.Blast

class LinearDecreaseBlastRadiusPattern(private val radiusIncreaseRate: Float) : BlastRadiusPattern() {
    override fun calculateRadius(blast: Blast, secondsElapsed: Float): Float {
        return with(blast) {
            radiusPx + (radiusPx * radiusIncreaseRate * secondsElapsed)
        }
    }
}