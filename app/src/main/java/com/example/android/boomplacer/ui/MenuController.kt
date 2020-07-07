package com.example.android.boomplacer.ui

import android.app.Activity
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.android.boomplacer.MainViewModel
import com.example.android.boomplacer.databinding.FragmentUiBinding
import com.example.android.boomplacer.extensions.exitApplication
import com.example.android.boomplacer.ui.interfaces.MainMenuButtons
import com.example.android.boomplacer.ui.interfaces.StartingMenuButtons

class MenuController private constructor(
    private val binding: FragmentUiBinding,
    private val viewModel: MainViewModel
) :
    StartingMenuButtons,
    MainMenuButtons {

    companion object {
        fun bind(binding: FragmentUiBinding, viewModel: MainViewModel) {
            with(binding.mainMenu) {
                this.controller = MenuController(binding, viewModel)
                this.viewModel = viewModel
            }
        }
    }

    //    private val startingMenuFrame
    private val mainMenuFrame = binding.mainMenu.menuMainFrame
    private val levelSelectorFrame = binding.levelSelector.levelSelectorMainFrame

    override fun pressResume() {
        mainMenuFrame.visibility = View.GONE
        viewModel.game.unPauseGame()
    }

    override fun pressNewGame() {
        mainMenuFrame.visibility = View.GONE
        levelSelectorFrame.visibility = View.VISIBLE
    }

    override fun pressResults() {
        //TODO("Not yet implemented")
    }

    override fun pressExit() {
        exitApplication(binding.root.context as Activity)
    }
}