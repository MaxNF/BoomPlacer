package com.example.android.boomplacer.ui

import android.app.Activity
import android.view.View
import com.example.android.boomplacer.MainViewModel
import com.example.android.boomplacer.databinding.FragmentUiBinding
import com.example.android.boomplacer.extensions.exitApplication
import com.example.android.boomplacer.ui.interfaces.LoseDialogButtons
import com.example.android.boomplacer.ui.interfaces.WinCategoryDialogButtons
import com.example.android.boomplacer.ui.interfaces.WinDialogButtons

class WinLoseDialogController(
    private val binding: FragmentUiBinding,
    private val viewModel: MainViewModel
) : LoseDialogButtons, WinDialogButtons, WinCategoryDialogButtons {

    companion object {
        fun bind(binding: FragmentUiBinding, viewModel: MainViewModel) {
            val controller = WinLoseDialogController(binding, viewModel)

            with(binding.winDialog) {
                this.controller = controller
                this.viewModel = viewModel
            }

            with(binding.winCategoryDialog) {
                this.controller = controller
                this.viewModel = viewModel
            }

            with(binding.loseDialog) {
                this.controller = controller
                this.viewModel = viewModel
            }
        }
    }

    private val levelSelectorFrame = binding.levelSelector.levelSelectorMainFrame
    private val loseDialogFrame = binding.loseDialog.loseDialogMainFrame
    private val winDialogFrame = binding.winDialog.winDialogMainFrame
    private val winCategoryDialogFrame = binding.winCategoryDialog.winCategoryDialogMainFrame

    override fun pressNewGame() {
        hideAllDialogs()
        levelSelectorFrame.visibility = View.VISIBLE
    }

    override fun pressExit() {
        exitApplication(binding.root.context as Activity)
    }

    override fun pressNextLevel() {
        viewModel.game.initAndStartNextLevel()
        hideAllDialogs()
    }

    private fun hideAllDialogs() {
        loseDialogFrame.visibility = View.GONE
        winDialogFrame.visibility = View.GONE
        winCategoryDialogFrame.visibility = View.GONE
    }
}