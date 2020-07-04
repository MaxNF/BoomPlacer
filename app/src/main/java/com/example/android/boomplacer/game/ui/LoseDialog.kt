package com.example.android.boomplacer.game.ui

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.android.boomplacer.R
import com.example.android.boomplacer.game.GameFlow

class LoseDialog(
    private val activity: Activity,
    private val gameFlow: GameFlow,
    private val userInterface: UserInterface
) {

    private val loseDialogMainFrame = activity.findViewById<ViewGroup>(R.id.lose_dialog_main_frame)
    private val newGameButton = loseDialogMainFrame.findViewById<Button>(R.id.button_new_game)
    private val exitButton = loseDialogMainFrame.findViewById<Button>(R.id.button_exit)

    init {
        newGameButton.setOnClickListener {
            userInterface.levelSelector.show()
            hide()
        }
        exitButton.setOnClickListener {
            userInterface.exit()
        }
    }

    fun show() {
        loseDialogMainFrame.visibility = View.VISIBLE
    }

    fun hide() {
        loseDialogMainFrame.visibility = View.GONE
    }

}