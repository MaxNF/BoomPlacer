package com.example.android.boomplacer.game

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.android.boomplacer.R
import com.example.android.boomplacer.model.gameobjects.factories.BombFactory
import com.example.android.boomplacer.model.gameobjects.factories.TargetFactory

class UserInterface(private val activity: Activity, private val gameFlow: GameFlow) {
    private val defaultValue = "0"

    private val menuButton: ImageButton = activity.findViewById(R.id.button_menu)
    private val menuMainFrame: ViewGroup = activity.findViewById(R.id.menu_main_frame)
    private val menuNewGameButton: Button = activity.findViewById(R.id.button_new_game)
    private val menuResumeButton: Button = activity.findViewById(R.id.button_resume)
    private val menuExitButton: Button = activity.findViewById(R.id.button_exit)
    private val menuBestResultsButton: Button = activity.findViewById(R.id.button_best_results)

    private val scoreView: TextView = activity.findViewById(R.id.score_value)
    private var scoreValue: Int = 0

    private val bombsView: TextView = activity.findViewById(R.id.remaining_bombs_value)
    private var bombsValue: Int = 0

    private val targetsView: TextView = activity.findViewById(R.id.remaining_targets_value)
    private var targetsValue: Int = 0

    init {
        menuButton.setOnClickListener { showMenu() }
        menuNewGameButton.setOnClickListener {
            startNewGame()
            hideMenu()
        }
        menuResumeButton.setOnClickListener {
            hideMenu()
        }
        menuExitButton.setOnClickListener {
            exit()
        }
        menuBestResultsButton.setOnClickListener {

        }
    }

    fun updateScore(newValue: Int) {
        if (scoreValue != newValue) {
            scoreValue = newValue
            scoreView.text = newValue.toString()
        }
    }

    fun updateBombs(newValue: Int) {
        if (bombsValue != newValue) {
            bombsValue = newValue
            bombsView.text = newValue.toString()
        }
    }

    fun updateTargets(newValue: Int) {
        if (targetsValue != newValue) {
            targetsValue = newValue
            targetsView.text = newValue.toString()
        }
    }

    fun updateUi(scoreValue: Int, bombsValue: Int, targetsValue: Int) {
        updateScore(scoreValue)
        updateBombs(bombsValue)
        updateTargets(targetsValue)
    }

    fun resetScore() {
        scoreView.text = defaultValue
    }

    fun resetBombs() {
        bombsView.text = defaultValue
    }

    fun resetTargets() {
        targetsView.text = defaultValue
    }

    fun reset() {
        resetScore()
        resetBombs()
        resetTargets()
    }

    fun showMenu() {
        gameFlow.pauseGame()
        menuMainFrame.visibility = View.VISIBLE
    }

    fun hideMenu() {
        menuMainFrame.visibility = View.INVISIBLE
        gameFlow.unPauseGame()
    }

    fun exit() {
        activity.finishAndRemoveTask()
    }

    fun showBestResults() {
        TODO()
    }

    private fun startNewGame() {
        gameFlow.initNewGame(10)
        gameFlow.startGame()
    }

    fun showWinView() {

    }

    fun showNoMoreBombsView() {

    }
}