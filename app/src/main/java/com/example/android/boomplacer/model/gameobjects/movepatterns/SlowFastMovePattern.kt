package com.example.android.boomplacer.model.gameobjects.movepatterns

import android.util.Log
import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.base.GameObject

class SlowFastMovePattern(
    minLevelCategory: LevelCategory,
    patternDifficulty: Int,
    accelSpeedPercent: Float,
    private val accelDurationSec: Float
) : MovePattern(minLevelCategory, patternDifficulty) {
    private val accelSpeedPortion = accelSpeedPercent / 100f

    private var currentDuration: Float = 0f
    private var accelSpeedPx: Float? = null
    override fun applyPattern(gameObject: GameObject, secondsElapsed: Float) {
        super.applyPattern(gameObject, secondsElapsed)
        val vel = gameObject.velocityPx
        val mag = vel.getMagnitude()
        val ang = vel.getAngle()

        if (accelSpeedPx == null) {
            accelSpeedPx = mag * accelSpeedPortion
            Log.d("Game", "INITIAL MAGNITUDE: $mag, ACCEL SPEED: $accelSpeedPx")
        }
        if (currentDuration >= accelDurationSec) {
            Log.d("Game", "CHANGED MAG: $mag")
            accelSpeedPx = accelSpeedPx!! * -1
            currentDuration = 0f
        }
        gameObject.velocityPx = vel + Vector2.create(ang, accelSpeedPx!!)
        gameObject.positionPx += gameObject.velocityPx * secondsElapsed
        currentDuration += secondsElapsed
    }
}