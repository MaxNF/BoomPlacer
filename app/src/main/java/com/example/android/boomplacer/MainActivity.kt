package com.example.android.boomplacer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.android.boomplacer.game.GameView
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.gameobjects.GameState
import com.example.android.boomplacer.gameobjects.factories.BombFactory
import com.example.android.boomplacer.gameobjects.factories.TargetFactory
import java.lang.UnsupportedOperationException

class MainActivity : AppCompatActivity() {
    private lateinit var game: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = GameView(this, ObjectManager())
        val container = findViewById<FrameLayout>(R.id.game_container)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        container.addView(game, params)

        game.showFramerate = true
        setUpListeners()
        subscribeToEvents()
    }

    private fun setUpListeners() {
        findViewById<ImageButton>(R.id.button_menu).setOnClickListener {
            game.pauseGame()
            launchMenu()
        }
//        findViewById<Button>(R.id.button_new_game).setOnClickListener {
//            startNewGame()
//        }
//        findViewById<Button>(R.id.button_pause).setOnClickListener {
//            if (game.isPaused()) {
//                game.unPauseGame()
//                (it as Button).text = "Pause"
//            } else {
//                game.pauseGame()
//                (it as Button).text = "Resume"
//            }
//        }
    }

    private fun launchMenu() {

    }

    private fun subscribeToEvents() {
        game.gameFinished.observe(this, Observer { gameState ->
            when (gameState) {
                GameState.WIN_TARGETS_DESTROYED -> Toast.makeText(
                    this,
                    "YOU WIN!",
                    Toast.LENGTH_LONG
                ).show()
                GameState.LOSE_NO_BOMBS_LEFT -> Toast.makeText(
                    this,
                    "YOU LOSE! NO MORE BOMBS LEFT.",
                    Toast.LENGTH_LONG
                ).show()
                GameState.LOSE_TIME_OVER -> Toast.makeText(
                    this,
                    "YOU LOSE! TIME IS OVER",
                    Toast.LENGTH_LONG
                ).show()
                else -> throw UnsupportedOperationException("Unsupported game state")
            }
        })
    }

    private fun startNewGame() {
        val targetFactory = TargetFactory(resources)
        val bombFactory = BombFactory(resources)
        game.initNewGame(targetFactory.createLinearTargets(3), bombFactory.createStaticBombs(5))
        game.startGame()
    }
}
