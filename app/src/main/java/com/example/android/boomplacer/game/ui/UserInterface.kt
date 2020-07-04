package com.example.android.boomplacer.game.ui

import android.app.Activity
import android.widget.ImageButton
import android.widget.TextView
import com.example.android.boomplacer.R
import com.example.android.boomplacer.game.GameFlow

class UserInterface(private val activity: Activity, private val gameFlow: GameFlow) {
    private val defaultValue = 0

    private val menuButton: ImageButton = activity.findViewById(R.id.button_menu)

    private val scoreView: TextView = activity.findViewById(R.id.score_value)
    private var scoreValue: Int = 0

    private val bombsView: TextView = activity.findViewById(R.id.remaining_bombs_value)
    private var bombsValue: Int = 0

    private val targetsView: TextView = activity.findViewById(R.id.remaining_targets_value)
    private var targetsValue: Int = 0

    private val levelNumberView: TextView = activity.findViewById(R.id.level_number_value)

    val mainMenu = MainMenu(activity, gameFlow, this)
    val levelSelector = LevelSelector(activity, gameFlow, this)
    val startingMenu = StartingMenu(activity, gameFlow, this)
    val winDialog = WinDialog(activity, gameFlow, this)
    val winCategoryDialog = WinCategoryDialog(activity, gameFlow, this)
    val loseDialog = LoseDialog(activity, gameFlow, this)

    init {
        menuButton.setOnClickListener {
            mainMenu.show()
            gameFlow.pauseGame()
        }
    }

    private fun updateScore(newValue: Int) {
        if (scoreValue != newValue) {
            scoreValue = newValue
            scoreView.text = newValue.toString()
        }
    }

    private fun updateBombs(newValue: Int) {
        if (bombsValue != newValue) {
            bombsValue = newValue
            bombsView.text = newValue.toString()
        }
    }

    private fun updateTargets(newValue: Int) {
        if (targetsValue != newValue) {
            targetsValue = newValue
            targetsView.text = newValue.toString()
        }
    }

    fun updateLevelNumber(newValue: Int) {
        levelNumberView.text = newValue.toString()
    }

    fun updateUi(scoreValue: Int, bombsValue: Int, targetsValue: Int) {
        updateScore(scoreValue)
        updateBombs(bombsValue)
        updateTargets(targetsValue)
    }

    fun reset() {
        resetDisplay()
        resetValue()
    }

    private fun resetDisplay() {
        scoreView.text = defaultValue.toString()
        bombsView.text = defaultValue.toString()
        targetsView.text = defaultValue.toString()
    }

    private fun resetValue() {
        scoreValue = defaultValue
        bombsValue = defaultValue
        targetsValue = defaultValue
    }

    fun showBestResults() {
        TODO()
    }

    fun exit() {
        activity.finishAndRemoveTask()
    }
}