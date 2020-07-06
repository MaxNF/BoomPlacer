package com.example.android.boomplacer.ui

import android.view.View
import com.example.android.boomplacer.MainViewModel
import com.example.android.boomplacer.databinding.FragmentUiBinding
import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.ui.interfaces.LevelSelectorButtons

class LevelSelectorController(
    private val binding: FragmentUiBinding,
    private val viewModel: MainViewModel
) : LevelSelectorButtons {

    companion object {
        fun bind(binding: FragmentUiBinding, viewModel: MainViewModel) {
            with(binding.levelSelector) {
                this.controller = LevelSelectorController(binding, viewModel)
                this.viewModel = viewModel
            }
        }
    }

    private val levelSelectorFrame = binding.levelSelector.levelSelectorMainFrame

    override fun pressEasy() {
        startGameAndCloseMenu(LevelCategory.EASY)
    }

    override fun pressNormal() {
        startGameAndCloseMenu(LevelCategory.NORMAL)
    }

    override fun pressHard() {
        startGameAndCloseMenu(LevelCategory.HARD)
    }

    override fun pressVeryHard() {
        startGameAndCloseMenu(LevelCategory.VERY_HARD)
    }

    override fun pressInsane() {
        startGameAndCloseMenu(LevelCategory.INSANE)
    }

    private fun startGameAndCloseMenu(levelCategory: LevelCategory) {
        viewModel.game.initNewGameAndStart(levelCategory)
        levelSelectorFrame.visibility = View.GONE
    }
}