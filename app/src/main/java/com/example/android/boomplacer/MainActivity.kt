package com.example.android.boomplacer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.android.boomplacer.game.Game
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.game.UserInterface

class MainActivity : AppCompatActivity() {
    private lateinit var game: Game
    private val objectManager = ObjectManager()
    private lateinit var userInterface: UserInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = Game(this, objectManager)
        val container = findViewById<FrameLayout>(R.id.game_container)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        container.addView(game, params)

        game.showFramerate = true
        userInterface = UserInterface(this, game)
        game.attachUserInterface(userInterface)
    }
}
