package com.example.android.boomplacer.model.gameobjects.movepatterns

import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.gameobjects.base.GameObject
import kotlin.math.PI

/**
 * @property currentZigZagTime required time to change the direction of zig-zag (seconds)
 * */
class ZigZagMovePattern(
    minLevelCategory: LevelCategory,
    patternDifficulty: Int,
    private var zigZigDurationSec: Float
) : MovePattern(minLevelCategory, patternDifficulty) {
    private var currentZigZagTime: Float = 0f
    private var reversed = false
    override fun applyPattern(gameObject: GameObject, secondsElapsed: Float) {
        super.applyPattern(gameObject, secondsElapsed)
        if (currentZigZagTime > zigZigDurationSec) {
            currentZigZagTime -= zigZigDurationSec
            val angle = if (reversed) {
                -(PI / 2.0).toFloat()
            } else {
                (PI / 2.0).toFloat()
            }
            reversed = !reversed
            gameObject.velocityPx.rotate(angle)
        }
        gameObject.positionPx += gameObject.velocityPx * secondsElapsed
        currentZigZagTime += secondsElapsed
    }
}