package com.example.android.boomplacer

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import com.example.android.boomplacer.extensions.createDebugLevel
import com.example.android.boomplacer.game.Game
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.game.UserInterface
import com.example.android.boomplacer.gamedata.LevelCategory
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import com.example.android.boomplacer.service.builders.LevelBuilder

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
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
