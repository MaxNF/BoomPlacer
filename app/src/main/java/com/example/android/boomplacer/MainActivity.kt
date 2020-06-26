package com.example.android.boomplacer

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import com.example.android.boomplacer.gameobjects.Target
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.game.GameView
import com.example.android.boomplacer.gameobjects.Blast
import com.example.android.boomplacer.gameobjects.Bomb

class MainActivity : AppCompatActivity() {
    private lateinit var game: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = GameView(this)
        val container = findViewById<FrameLayout>(R.id.game_container)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        container.addView(game, params)

        game.showFramerate = true
        setUpListeners()
    }

    private fun setUpListeners() {
        findViewById<Button>(R.id.button_new_game).setOnClickListener {
            startNewGame()
        }
        findViewById<Button>(R.id.button_pause).setOnClickListener {
            if (game.isPaused()) {
                game.unPauseGame()
                (it as Button).text = "Pause"
            } else {
                game.pauseGame()
                (it as Button).text = "Resume"
            }
        }
    }

    private fun startNewGame() {
        val targets = listOf(
            Target.simpleLinearTarget(
                BitmapFactory.decodeResource(resources, R.drawable.ic_star), Vector2(50f, 50f)
            )
        )
        val bombs = listOf(
            Bomb.simpleStaticBomb(
                BitmapFactory.decodeResource(resources, R.drawable.ic_bomb),
                Blast.simpleStaticBlast(Paint().apply {
                    color = Color.RED
                    alpha = 80
                })
            )
        )
        game.initNewGame(targets, bombs)
        game.startGame()
    }
}
