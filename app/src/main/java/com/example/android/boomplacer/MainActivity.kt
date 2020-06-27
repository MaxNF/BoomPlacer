package com.example.android.boomplacer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.android.boomplacer.game.GameView
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.game.ScoreManager
import com.example.android.boomplacer.model.gameobjects.GameState
import com.example.android.boomplacer.model.gameobjects.factories.BombFactory
import com.example.android.boomplacer.model.gameobjects.factories.TargetFactory
import com.example.android.boomplacer.ui.MenuFragment
import org.w3c.dom.Text
import java.lang.UnsupportedOperationException

class MainActivity : AppCompatActivity() {
    private lateinit var game: GameView
    private val scoreManager = ScoreManager()
    private val objectManager = ObjectManager()

    private lateinit var scoreTextView: TextView
    private lateinit var bombsLeftTextView: TextView
    private lateinit var targetsLeftTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreTextView = findViewById(R.id.score_value)
        bombsLeftTextView = findViewById(R.id.remaining_bombs_value)
        targetsLeftTextView = findViewById(R.id.remaining_targets_value)

        game = GameView(this, objectManager, scoreManager)
        val container = findViewById<FrameLayout>(R.id.game_container)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        container.addView(game, params)

        game.showFramerate = true
        setUpListeners()
        subscribeToGameEvents()
    }

    private fun setUpListeners() {
        findViewById<ImageButton>(R.id.button_menu).setOnClickListener {
            game.pauseGame()
            launchMenu()
        }
    }

    private fun launchMenu() {
        game.pauseGame()
        val menuFragment = MenuFragment(game)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.menu_fragment_container, menuFragment)
        transaction.commit()
        subscribeToMenuEvents(menuFragment)
    }

    private fun subscribeToMenuEvents(menuFragment: MenuFragment) {
        menuFragment.resumeButtonClicked.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                closeMenu()
            }
        })

        menuFragment.bestResultsButtonClicked.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                TODO()
            }
        })

        menuFragment.exitButtonClicked.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                exit()
            }
        })

        menuFragment.newGameButtonClicked.observe(this, Observer {
            closeMenu()
            startNewGame()
        })
    }

    private fun closeMenu() {
        val menuFragment = supportFragmentManager.fragments[0] as MenuFragment
        unsubscribeFromMenuEvents(menuFragment)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(menuFragment)
        transaction.commit()
        game.unPauseGame()
    }

    private fun exit() {
        finishAndRemoveTask()
    }

    private fun unsubscribeFromMenuEvents(menuFragment: MenuFragment) {
        menuFragment.resumeButtonClicked.removeObservers(this)
    }

    private fun subscribeToGameEvents() {
        game.gameFinished.observe(this, Observer {
            it.getContentIfNotHandled()?.let { gameState ->
                when (gameState) {
                    GameState.WIN_TARGETS_DESTROYED -> Toast.makeText(
                        this,
                        "YOU WIN! YOUR SCORE: ${scoreManager.currentScore}",
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
            }
        })

        game.scoreRefreshed.observe(this, Observer {
            it.getContentIfNotHandled()?.let { receivedScore ->
                scoreTextView.text = receivedScore.toString()
            }
        })

        game.bombsRefreshed.observe(this, Observer {
            it.getContentIfNotHandled()?.let { bombsLeft ->
                bombsLeftTextView.text = bombsLeft.toString()
            }
        })

        game.targetsRefreshed.observe(this, Observer {
            it.getContentIfNotHandled()?.let { targetsLeft ->
                targetsLeftTextView.text = targetsLeft.toString()
            }
        })
    }

    private fun startNewGame() {
        val targetFactory = TargetFactory(resources)
        val bombFactory = BombFactory(resources)
        val targets = bombFactory.createStaticBombs(5)
        val bombs = targetFactory.createLinearTargets(3)
        game.initNewGame(bombs, targets)
        game.startGame()
    }
}
