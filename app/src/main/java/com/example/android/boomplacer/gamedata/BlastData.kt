package com.example.android.boomplacer.gamedata

class BlastData {
    companion object {
        const val BASE_SPEED: Float = 0f
        const val BASE_RADIUS: Float = 50f

        val RADIUS_MODIFIER_FORMULA: (difficultyValue: Int) -> Float = TODO()
        val RADIUS_DECREASE_MODIFIER_FORMULA: (difficultyValue: Int) -> Float = TODO()
    }
}