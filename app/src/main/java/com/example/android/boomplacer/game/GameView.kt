package com.example.android.boomplacer.game

import android.content.Context
import android.graphics.Color
import android.view.MotionEvent
import android.view.SurfaceView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.boomplacer.gameobjects.base.Bomb
import com.example.android.boomplacer.gameobjects.GameState
import com.example.android.boomplacer.gameobjects.base.Target

class GameView(context: Context, private val objectManager: ObjectManager) : SurfaceView(context) {
    private lateinit var gameLoop: GameLoop
    var targetFramerate: Int = 60
    var showFramerate = false

    private val _gameFinished: MutableLiveData<GameState> = MutableLiveData()
    val gameFinished: LiveData<GameState> = _gameFinished


    fun startGame() {
        if (::gameLoop.isInitialized) {
            gameLoop.running = true
            objectManager.placeTarget()
            gameLoop.start()
        }
    }

    fun stopGame() {
        if (::gameLoop.isInitialized) {
            gameLoop.running = false
        }
    }

    fun pauseGame() {
        if (::gameLoop.isInitialized) {
            gameLoop.paused = true
        }
    }

    fun unPauseGame() {
        if (::gameLoop.isInitialized) {
            gameLoop.paused = false
        }
    }

    fun isPaused() = gameLoop.paused

    fun initNewGame(targets: List<Target>, bombs: List<Bomb>) {
        if (::gameLoop.isInitialized && gameLoop.running) stopGame()
        gameLoop = GameLoop(this)
        objectManager.clearAll()
        objectManager.addPendingTargets(targets)
        objectManager.addInventoryBombs(bombs)
    }

    fun updateGameState(oneFrameMillis: Long) {
        val secondsElapsed = oneFrameMillis / 1000F
        updateBombsState(secondsElapsed)
        updateBlastsState(secondsElapsed)
        updateTargetsState(secondsElapsed)

        val gameState = calculateGameState()
        if (gameState != GameState.RUNNING) {
            endGame(gameState)
        }
    }

    private fun updateBombsState(secondsElapsed: Float) {
        val iterator = objectManager.placedBombs.iterator()
        while (iterator.hasNext()) {
            val bomb = iterator.next()
            if (bomb.updateState(width, height, secondsElapsed, objectManager)) {
                iterator.remove()
            }
        }
    }

    private fun updateBlastsState(secondsElapsed: Float) {
        val iterator = objectManager.placedBlasts.iterator()
        while (iterator.hasNext()) {
            val blast = iterator.next()
            if (blast.updateState(width, height, secondsElapsed, objectManager)) {
                iterator.remove()
            }
        }
    }

    private fun updateTargetsState(secondsElapsed: Float) {
        val iterator = objectManager.placedTargets.iterator()
        while (iterator.hasNext()) {
            val target = iterator.next()
            if (target.updateState(width, height, secondsElapsed, objectManager)) {
                iterator.remove()
            }
        }

        if (objectManager.placedTargets.isEmpty()) {
            objectManager.placeTarget()
        }
    }

    private fun calculateGameState(): GameState {
        val anyTargetsLeft =
            objectManager.pendingTargets.isNotEmpty() || objectManager.placedTargets.isNotEmpty()
        val anyBombsLeft =
            objectManager.inventoryBombs.isNotEmpty() || objectManager.placedBombs.isNotEmpty()
        val anyBlastsProceed = objectManager.placedBlasts.isNotEmpty()
        return when {
            anyTargetsLeft && !anyBombsLeft && !anyBlastsProceed -> GameState.LOSE_NO_BOMBS_LEFT
            !anyTargetsLeft -> GameState.WIN_TARGETS_DESTROYED
            else -> GameState.RUNNING
        }
    }

    private fun endGame(gameState: GameState) {
        stopGame()
        _gameFinished.postValue(gameState)
    }

    fun draw() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawColor(Color.LTGRAY)
            objectManager.placedBombs.forEach { it.draw(canvas) }
            objectManager.placedBlasts.forEach { it.draw(canvas) }
            objectManager.placedTargets.forEach { it.draw(canvas) }
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            objectManager.placeBomb(event.x, event.y)
        }
        return true
    }
}