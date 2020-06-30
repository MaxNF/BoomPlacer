package com.example.android.boomplacer.game

import android.content.Context
import android.graphics.Color
import android.view.MotionEvent
import android.view.SurfaceView
import com.example.android.boomplacer.model.gameobjects.GameState
import com.example.android.boomplacer.model.gameobjects.levels.Level
import com.example.android.boomplacer.model.gameobjects.factories.BombFactory
import com.example.android.boomplacer.model.gameobjects.factories.TargetFactory
import java.lang.IllegalStateException

class Game(
    context: Context,
    private val objectManager: ObjectManager
) : SurfaceView(context), GameFlow {
    private lateinit var gameLoop: GameLoop
    var targetFramerate: Int = 60
    var showFramerate = false

    private lateinit var userInterface: UserInterface
    private var level = 0

    fun attachUserInterface(userInterface: UserInterface) {
        this.userInterface = userInterface
    }

    override fun startGame() {
        if (::gameLoop.isInitialized) {
            gameLoop.running = true
            objectManager.placeTarget()
            gameLoop.start()
        }
    }

    override fun stopGame() {
        if (::gameLoop.isInitialized) {
            gameLoop.running = false
        }
    }

    override fun pauseGame() {
        if (::gameLoop.isInitialized) {
            gameLoop.paused = true
        }
    }

    override fun unPauseGame() {
        if (::gameLoop.isInitialized) {
            gameLoop.paused = false
        }
    }

    override fun isPaused() = gameLoop.paused

    override fun initNewGame(levelId: Int) {
        if (::gameLoop.isInitialized && gameLoop.running) stopGame()
        gameLoop = GameLoop(this)
        objectManager.reset()
        userInterface.reset()

        val level =
            Level(
                levelId,
                TargetFactory(context.resources),
                BombFactory(context.resources),
                width,
                height
            )
        objectManager.addPendingTargets(level.targets)
        objectManager.addInventoryBombs(level.bombs)
    }

    fun updateGameState(oneFrameMillis: Long) {
        val secondsElapsed = oneFrameMillis / 1000F
        updateBombsState(secondsElapsed)
        objectManager.placeBlastsFromExpiredBombs()
        updateBlastsState(secondsElapsed)
        updateTargetsState(secondsElapsed)
        updateAntiTargetsState(secondsElapsed)
        objectManager.calculateScore()

        val gameState = calculateGameState()
        if (gameFinished(gameState)) {
            endGame(gameState)
        }

        if (shouldPlaceNextTarget()) {
            objectManager.placeTarget()
        }
    }

    private fun gameFinished(gameState: GameState) = gameState != GameState.RUNNING

    private fun shouldPlaceNextTarget() =
        objectManager.noBombsOrBlastsOnScreen && objectManager.noTargetsOnScreen

    private fun updateBombsState(secondsElapsed: Float) {
        val iterator = objectManager.placedBombs.iterator()
        while (iterator.hasNext()) {
            val bomb = iterator.next()
            if (bomb.updateState(width, height, secondsElapsed, objectManager)) {
                iterator.remove()
                objectManager.expiredBombs.add(bomb)
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
                objectManager.destroyedTargets.add(target)
            }
        }
    }

    private fun updateAntiTargetsState(secondsElapsed: Float) {
        val iterator = objectManager.placedAntiTargets.iterator()
        while (iterator.hasNext()) {
            val antiTarget = iterator.next()
            val isDestroyed = antiTarget.updateState(width, height, secondsElapsed, objectManager)
            if (isDestroyed) {
                iterator.remove()
                objectManager.destroyedTargets.add(antiTarget)
            }
        }
    }

    private fun calculateGameState(): GameState {
        val anyTargetsLeft =
            objectManager.pendingTargets.isNotEmpty() || objectManager.placedTargets.isNotEmpty()
        val anyBombsLeft =
            objectManager.inventoryBombs.isNotEmpty() || objectManager.placedBombs.isNotEmpty()
        val anyBlastsProceed = objectManager.placedBlasts.isNotEmpty()
        val anyAntiTargetsDestroyed = objectManager.destroyedAntiTargets.isNotEmpty()
        return when {
            anyAntiTargetsDestroyed && !anyBlastsProceed -> GameState.LOSE_ANTI_TARGET_DESTROYED
            anyTargetsLeft && !anyBombsLeft && !anyBlastsProceed -> GameState.LOSE_NO_BOMBS_LEFT
            !anyTargetsLeft && !anyBlastsProceed -> GameState.WIN_TARGETS_DESTROYED
            else -> GameState.RUNNING
        }
    }

    private fun endGame(gameState: GameState) {
        when (gameState) {
            GameState.WIN_TARGETS_DESTROYED -> {
                objectManager.calculateFinalScore()
                userInterface.showWinView()
            }
            GameState.LOSE_ANTI_TARGET_DESTROYED -> {
                userInterface.showAntiTargetDestroyedView()
            }
            GameState.LOSE_NO_BOMBS_LEFT -> {
                userInterface.showNoMoreBombsView()
            }
            else -> throw IllegalStateException("Unsupported game state for ending the game")
        }
        stopGame()
    }

    fun draw() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawColor(Color.LTGRAY)
            objectManager.placedBombs.forEach { it.draw(canvas) }
            objectManager.placedBlasts.forEach { it.draw(canvas) }
            objectManager.placedTargets.forEach { it.draw(canvas) }
            holder.unlockCanvasAndPost(canvas)

            userInterface.updateUi(
                objectManager.score,
                objectManager.inventoryBombsCount,
                objectManager.targetsCount
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && objectManager.noBombsOrBlastsOnScreen) {
            objectManager.placeBomb(event.x, event.y)
        }
        return true
    }
}