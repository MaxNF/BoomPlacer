package com.example.android.boomplacer.game.ui

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.android.boomplacer.R
import com.example.android.boomplacer.extensions.createDebugLevel
import com.example.android.boomplacer.game.Game
import com.example.android.boomplacer.game.GameFlow
import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty

class LevelSelector(
    private val activity: Activity,
    private val gameFlow: GameFlow,
    private val userInterface: UserInterface
) {
    private val levelSelectorMainFrame: ViewGroup =
        activity.findViewById(R.id.level_selector_main_frame)
    private val levelSelectorEasyButton: Button = levelSelectorMainFrame.findViewById(R.id.level_easy_button)
    private val levelSelectorNormalButton: Button = levelSelectorMainFrame.findViewById(R.id.level_normal_button)
    private val levelSelectorHardButton: Button = levelSelectorMainFrame.findViewById(R.id.level_hard_button)
    private val levelSelectorVeryHardButton: Button =
        activity.findViewById(R.id.level_very_hard_button)
    private val levelSelectorInsaneButton: Button = levelSelectorMainFrame.findViewById(R.id.level_insane_button)

    init {
        levelSelectorEasyButton.setOnClickListener {
            startNewGame(LevelDifficulty(LevelCategory.EASY, 0))
        }
        levelSelectorNormalButton.setOnClickListener {
            startNewGame(LevelDifficulty(LevelCategory.NORMAL, 0))
        }
        levelSelectorHardButton.setOnClickListener {
            startNewGame(LevelDifficulty(LevelCategory.HARD, 0))
        }
        levelSelectorVeryHardButton.setOnClickListener {
            startNewGame(LevelDifficulty(LevelCategory.VERY_HARD, 0))
        }
        levelSelectorInsaneButton.setOnClickListener {
            startNewGame(LevelDifficulty(LevelCategory.INSANE, 0))
        }
    }

    fun show() {
        levelSelectorMainFrame.visibility = View.VISIBLE
    }

    fun hide() {
        levelSelectorMainFrame.visibility = View.GONE
    }

    private fun startNewGame(levelDifficulty: LevelDifficulty) {
//        gameFlow.initNewGameAndStart(levelDifficulty)
        hide()
    }
}