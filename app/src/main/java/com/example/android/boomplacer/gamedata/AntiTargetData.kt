package com.example.android.boomplacer.gamedata

class AntiTargetData {
    companion object {
        const val BASE_ANTI_TARGET_SPEED: Float = 100f
        const val BASE_ANTI_TARGET_SPEED_MODIFIER: Float = 10f
        val SPEED_MODIFIER_FORMULA: (difficultyValue: Int) -> Float = TODO()
        val RADIUS_MODIFIER_FORMULA: (difficultyValue: Int) -> Float = TODO()
    }
}