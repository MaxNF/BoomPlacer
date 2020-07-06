package com.example.android.boomplacer

import androidx.lifecycle.ViewModel
import com.example.android.boomplacer.game.Game
import com.example.android.boomplacer.game.GameFlow

class MainViewModel : ViewModel() {
    lateinit var game: Game
        private set

    fun attachGame(game: Game) {
        if (gameAttached()) {
            throw Exception("Game already attached.")
        }
        this.game = game
    }

    fun gameAttached() = ::game.isInitialized
}