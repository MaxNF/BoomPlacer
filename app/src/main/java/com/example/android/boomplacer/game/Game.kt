package com.example.android.boomplacer.game

import android.content.Context
import android.graphics.Color
import android.view.MotionEvent
import android.view.SurfaceView
import com.example.android.boomplacer.extensions.createDebugLevel
import com.example.android.boomplacer.extensions.runOnMainThread
import com.example.android.boomplacer.game.ui.UserInterface
import com.example.android.boomplacer.gamedata.LevelData
import com.example.android.boomplacer.model.gameobjects.GameState
import com.example.android.boomplacer.model.gameobjects.levels.Level
import com.example.android.boomplacer.model.gameobjects.levels.LevelDifficulty
import java.lang.IllegalStateException

class Game(
    context: Context,
    private val objectManager: ObjectManager
) : SurfaceView(context), GameFlow {
    private val TAG = "Game"

    private lateinit var gameLoop: GameLoop
    var targetFramerate: Int = 60
    var showFramerate = false

    private lateinit var userInterface: UserInterface
    private lateinit var currentDifficulty: LevelDifficulty

    fun attachUserInterface(userInterface: UserInterface) {
        this.userInterface = userInterface
    }

    override fun startGame() {
        if (::gameLoop.isInitialized) {
            runOnMainThread(context) { userInterface.updateLevelNumber(currentDifficulty.difficultyOffset + 1) }
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

    private fun initNewGame(level: Level) {
        resetGameState()
        addGameObjects(level)
    }

    override fun initNewGameAndStart(levelDifficulty: LevelDifficulty) {
        currentDifficulty = levelDifficulty
        val level = createDebugLevel(this, levelDifficulty)
        initNewGame(level)
        unPauseGame()
        startGame()
    }

    override fun initAndStartNextLevel() {
        val newDifficulty =
            LevelDifficulty(currentDifficulty.levelCategory, currentDifficulty.difficultyOffset + 1)
        currentDifficulty = newDifficulty
        val level = createDebugLevel(this, newDifficulty)
        initNewGame(level)
        unPauseGame()
        startGame()
    }

    private fun resetGameState() {
        if (::gameLoop.isInitialized && gameLoop.running) {
            stopGame()
        }
        gameLoop = GameLoop(this)
        objectManager.reset()
        userInterface.reset()
    }

    private fun addGameObjects(level: Level) {
        objectManager.addPendingTargets(level.targets)
        objectManager.addPendingAntiTargets(level.antiTargets)
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

        if (shouldPlaceNextTarget()) {
            objectManager.placeTarget()
        }

        val gameState = calculateGameState()
        if (gameFinished(gameState)) {
            endGame(gameState)
        }
    }

    private fun gameFinished(gameState: GameState) = gameState != GameState.RUNNING

    private fun shouldPlaceNextTarget() =
        objectManager.noBombsOnScreen && objectManager.noBlastsOnScreen && objectManager.noTargetsOnScreen

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
        return when {
            objectManager.isAntiTargetsDestroyed && objectManager.noBlastsOnScreen -> GameState.LOSE_ANTI_TARGET_DESTROYED
            objectManager.noTargetsOnScreen && objectManager.noBlastsOnScreen -> GameState.WIN_TARGETS_DESTROYED
            objectManager.noBombsOnScreen && objectManager.noBombsInInventory && objectManager.noBlastsOnScreen -> GameState.LOSE_NO_BOMBS_LEFT
            else -> GameState.RUNNING
        }
    }

    private fun endGame(gameState: GameState) {
        when (gameState) {
            GameState.WIN_TARGETS_DESTROYED -> {
                objectManager.calculateFinalScore()
                if (currentDifficulty.difficultyOffset == LevelData.FINAL_DIFFICULTY_OFFSET) {
                    runOnMainThread(context) { userInterface.winCategoryDialog.show() }
                } else {
                    runOnMainThread(context) { userInterface.winDialog.show() }
                }
            }
            GameState.LOSE_ANTI_TARGET_DESTROYED -> {
                runOnMainThread(context) { userInterface.loseDialog.show() }
            }
            GameState.LOSE_NO_BOMBS_LEFT -> {
                runOnMainThread(context) { userInterface.loseDialog.show() }
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

            runOnMainThread(context) {
                userInterface.updateUi(
                    objectManager.score,
                    objectManager.inventoryBombsCount,
                    objectManager.targetsCount
                )
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && objectManager.noBombsOnScreen && objectManager.noBlastsOnScreen) {
            objectManager.placeBomb(event.x, event.y)
        }
        return true
    }
}