package com.example.android.boomplacer.game.ui

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.android.boomplacer.R
import com.example.android.boomplacer.game.GameFlow

class WinDialog(
    private val activity: Activity,
    private val gameFlow: GameFlow,
    private val userInterface: UserInterface
) {

    private val winDialogMainFrame = activity.findViewById<ViewGroup>(R.id.win_dialog_main_frame)
    private val nextButton = winDialogMainFrame.findViewById<Button>(R.id.button_next_level)
    private val exitButton = winDialogMainFrame.findViewById<Button>(R.id.button_exit)

    init {
        nextButton.setOnClickListener {
            gameFlow.initAndStartNextLevel()
            hide()
        }
        exitButton.setOnClickListener { userInterface.exit() }
    }

    fun show() {
        winDialogMainFrame.visibility = View.VISIBLE
    }

    fun hide() {
        winDialogMainFrame.visibility = View.GONE
    }
}