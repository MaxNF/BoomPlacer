package com.example.android.boomplacer.service.builders

import android.graphics.Bitmap
import android.graphics.Paint
import com.example.android.boomplacer.model.gameobjects.levels.Level
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.service.factories.AntiTargetFactory
import com.example.android.boomplacer.service.factories.BlastFactory
import com.example.android.boomplacer.service.factories.BombFactory
import com.example.android.boomplacer.service.factories.TargetFactory

class LevelBuilder() : Builder() {
    var targetIcon: Bitmap? = null
        get() = field ?: throwPropertyNotSetException(::targetIcon.name)
    var antiTargetIcon: Bitmap? = null
        get() = field ?: throwPropertyNotSetException(::antiTargetIcon.name)
    var bombIcon: Bitmap? = null
        get() = field ?: throwPropertyNotSetException(::bombIcon.name)
    var blastPaint: Paint? = null
        get() = field ?: throwPropertyNotSetException(::blastPaint.name)
    var levelDifficulty: LevelDifficulty? = null
        get() = field ?: throwPropertyNotSetException(::levelDifficulty.name)
    var fieldWidth: Int? = null
        get() = field ?: throwPropertyNotSetException(::fieldWidth.name)
    var fieldHeight: Int? = null
        get() = field ?: throwPropertyNotSetException(::fieldHeight.name)

    fun build(): Level {
        val targetFactory = TargetFactory(targetIcon!!, fieldWidth!!, fieldHeight!!)
        val antiTargetFactory = AntiTargetFactory(antiTargetIcon!!, fieldWidth!!, fieldHeight!!)
        val blastFactory = BlastFactory(null, blastPaint!!)
        val bombFactory = BombFactory(blastFactory, bombIcon!!)
        return Level(
            generateName(levelDifficulty!!),
            targetFactory.create(levelDifficulty!!),
            antiTargetFactory.create(levelDifficulty!!),
            bombFactory.create(levelDifficulty!!)
        )
    }

    private fun generateName(levelDifficulty: LevelDifficulty): String {
        return "${levelDifficulty.levelCategory.name} - ${levelDifficulty.difficultyOffset}"
    }
}