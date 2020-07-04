package com.example.android.boomplacer.game.ui

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.android.boomplacer.R
import com.example.android.boomplacer.game.GameFlow

class MainMenu(
    private val activity: Activity,
    private val gameFlow: GameFlow,
    private val userInterface: UserInterface
) {

    private val menuMainFrame: ViewGroup = activity.findViewById(R.id.menu_main_frame)
    private val menuNewGameButton: Button = menuMainFrame.findViewById(R.id.button_new_game)
    private val menuResumeButton: Button = menuMainFrame.findViewById(R.id.button_resume)
    private val menuExitButton: Button = menuMainFrame.findViewById(R.id.button_exit)
    private val menuBestResultsButton: Button = menuMainFrame.findViewById(R.id.button_best_results)

    init {
        menuNewGameButton.setOnClickListener {
            userInterface.levelSelector.show()
            hide()
        }
        menuResumeButton.setOnClickListener {
            hide()
            gameFlow.unPauseGame()
        }
        menuExitButton.setOnClickListener {
            userInterface.exit()
        }
        menuBestResultsButton.setOnClickListener {

        }
    }

    fun show() {
        menuMainFrame.visibility = View.VISIBLE
    }

    fun hide() {
        menuMainFrame.visibility = View.GONE
    }
}