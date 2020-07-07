package com.example.android.boomplacer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import com.example.android.boomplacer.game.Game
import com.example.android.boomplacer.game.ObjectManager
import mikera.vectorz.Vector2

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val objectManager = ObjectManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!viewModel.gameAttached()) {
            viewModel.attachGame(Game(this, objectManager))
        }

        val container = findViewById<FrameLayout>(R.id.game_container)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        container.addView(viewModel.game, params)

    }
}
