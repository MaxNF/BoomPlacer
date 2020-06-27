package com.example.android.boomplacer.game

import android.content.Context
import android.graphics.Color
import android.view.MotionEvent
import android.view.SurfaceView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.boomplacer.model.Event
import com.example.android.boomplacer.model.gameobjects.base.Bomb
import com.example.android.boomplacer.model.gameobjects.GameState
import com.example.android.boomplacer.model.gameobjects.base.Target

class GameView(
    context: Context,
    private val objectManager: ObjectManager,
    private val scoreManager: ScoreManager
) : SurfaceView(context) {
    private lateinit var gameLoop: GameLoop
    var targetFramerate: Int = 60
    var showFramerate = false

    private val _gameFinished: MutableLiveData<Event<GameState>> = MutableLiveData()
    val gameFinished: LiveData<Event<GameState>> = _gameFinished

    private val _scoreRefreshed: MutableLiveData<Event<Int>> = MutableLiveData()
    val scoreRefreshed: LiveData<Event<Int>> = _scoreRefreshed

    private val _targetsRefreshed: MutableLiveData<Event<Int>> = MutableLiveData()
    val targetsRefreshed: LiveData<Event<Int>> = _targetsRefreshed

    private val _bombsRefreshed: MutableLiveData<Event<Int>> = MutableLiveData()
    val bombsRefreshed: LiveData<Event<Int>> = _bombsRefreshed


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
        scoreManager.clearScore()
        objectManager.addPendingTargets(targets)
        objectManager.addInventoryBombs(bombs)

        _bombsRefreshed.postValue(Event(objectManager.inventoryBombsCount()))
        _targetsRefreshed.postValue(Event(objectManager.pendingTargetsCount()))
        _scoreRefreshed.postValue(Event(scoreManager.currentScore))
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
            val isDestroyed = target.updateState(width, height, secondsElapsed, objectManager)
            if (isDestroyed) {
                iterator.remove()
                scoreManager.increaseScore(target)
                _scoreRefreshed.postValue(Event(scoreManager.currentScore))
            }
        }

        if (objectManager.placedTargets.isEmpty()) {
            if (objectManager.placeTarget()) {
                _targetsRefreshed.postValue(Event(objectManager.pendingTargetsCount()))
            }
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
        if (gameState == GameState.WIN_TARGETS_DESTROYED) {
            scoreManager.increaseScore(objectManager.inventoryBombs)
        }
        stopGame()
        _gameFinished.postValue(Event(gameState))
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
            if (objectManager.placeBomb(event.x, event.y)) {
                _bombsRefreshed.postValue(Event(objectManager.inventoryBombsCount()))
            }
        }
        return true
    }
}