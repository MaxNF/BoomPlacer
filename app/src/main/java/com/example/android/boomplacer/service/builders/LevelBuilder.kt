package com.example.android.boomplacer.service.builders

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import com.example.android.boomplacer.gamedata.AntiTargetData
import com.example.android.boomplacer.gamedata.BlastData
import com.example.android.boomplacer.gamedata.BombData
import com.example.android.boomplacer.gamedata.TargetData
import com.example.android.boomplacer.model.gameobjects.levels.Level
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.service.factories.AntiTargetFactory
import com.example.android.boomplacer.service.factories.BlastFactory
import com.example.android.boomplacer.service.factories.BombFactory
import com.example.android.boomplacer.service.factories.TargetFactory

class LevelBuilder(private val resources: Resources) : Builder() {
    var levelDifficulty: LevelDifficulty? = null
        get() = field ?: throwPropertyNotSetException(::levelDifficulty.name)
    var fieldWidth: Int? = null
        get() = field ?: throwPropertyNotSetException(::fieldWidth.name)
    var fieldHeight: Int? = null
        get() = field ?: throwPropertyNotSetException(::fieldHeight.name)

    fun build(): Level {
        val targetIcon = BitmapFactory.decodeResource(resources, TargetData.ICON_DRAWABLE_ID)
        val antiTargetIcon =
            BitmapFactory.decodeResource(resources, AntiTargetData.ICON_DRAWABLE_ID)
        val bombIcon = BitmapFactory.decodeResource(resources, BombData.ICON_DRAWABLE_ID)
        val blastPaint = BlastData.PAINT

        val targetFactory = TargetFactory(targetIcon, fieldWidth!!, fieldHeight!!)
        val antiTargetFactory = AntiTargetFactory(antiTargetIcon, fieldWidth!!, fieldHeight!!)
        val blastFactory = BlastFactory(null, blastPaint)
        val bombFactory = BombFactory(blastFactory, bombIcon)
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