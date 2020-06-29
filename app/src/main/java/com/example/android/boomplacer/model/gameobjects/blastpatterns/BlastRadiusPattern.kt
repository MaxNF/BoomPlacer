package com.example.android.boomplacer.model.gameobjects.blastpatterns

import com.example.android.boomplacer.model.gameobjects.blasts.Blast

abstract class BlastRadiusPattern {
    open fun apply(blast: Blast, secondsElapsed: Float) {
        blast.radiusPx = calculateRadius(blast, secondsElapsed)
    }

    protected abstract fun calculateRadius(blast: Blast, secondsElapsed: Float): Float
}