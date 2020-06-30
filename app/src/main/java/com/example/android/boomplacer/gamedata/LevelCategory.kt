package com.example.android.boomplacer.gamedata

enum class LevelCategory(val baseDifficulty: Int) {
    EASY(10), NORMAL(20), HARD(30), VERY_HARD(40), INSANE(50);

    operator fun compareTo(levelCategory: LevelCategory): Int {
        return this.baseDifficulty.compareTo(levelCategory.baseDifficulty)
    }
}