package com.example.android.boomplacer.game

import android.content.Context
import android.graphics.Color
import android.view.MotionEvent
import android.view.SurfaceView
import com.example.android.boomplacer.gameobjects.Blast
import com.example.android.boomplacer.gameobjects.Bomb
import com.example.android.boomplacer.gameobjects.Target
import java.util.*

class GameView(context: Context) : SurfaceView(context) {
    private lateinit var gameLoop: GameLoop
    private val placedTargets = mutableListOf<Target>()
    private val placedBombs = mutableListOf<Bomb>()
    private val placedBlasts = mutableListOf<Blast>()
    private val targets = LinkedList<Target>()
    private val bombs = LinkedList<Bomb>()
    var targetFramerate: Int = 60
    var showFramerate = false


    fun startGame() {
        if (::gameLoop.isInitialized) {
            gameLoop.running = true
            placeTarget()
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
        placedTargets.clear()
        this.targets.addAll(targets)
        this.bombs.addAll(bombs)
    }

    private fun placeBomb(x: Float, y: Float) {
        if (bombs.isNotEmpty()) {
            val bomb = bombs.pop()
            bomb.position.x = x
            bomb.position.y = y
            placedBombs.add(bomb)
        }
    }

    private fun placeTarget() {
        if (targets.isNotEmpty()) {
            placedTargets.add(targets.pop())
        }
    }

    private fun placeBlast(bomb: Bomb) {
        val blast = bomb.blast
        blast.position = bomb.position
        placedBlasts.add(blast)
    }

    fun updateGameState(oneFrameMillis: Long) {
        val secondsElapsed = oneFrameMillis / 1000F
        updateBombsState(secondsElapsed)
        updateBlastsState(secondsElapsed)
        updateTargetsState(secondsElapsed)
    }

    private fun updateBombsState(secondsElapsed: Float) {
        val iterator = placedBombs.iterator()
        while (iterator.hasNext()) {
            val bomb = iterator.next()
            if (!bomb.updateState(width, height, secondsElapsed)) {
                placeBlast(bomb)
                iterator.remove()
            }
        }
    }

    private fun updateBlastsState(secondsElapsed: Float) {
        val iterator = placedBlasts.iterator()
        while (iterator.hasNext()) {
            val blast = iterator.next()
            if (!blast.updateState(width, height, secondsElapsed)) {
                iterator.remove()
            }
        }
    }

    private fun updateTargetsState(secondsElapsed: Float) {
        val iterator = placedTargets.iterator()
        while (iterator.hasNext()) {
            val target = iterator.next()
            if (!target.updateState(width, height, secondsElapsed)) {
                iterator.remove()
            }
        }
    }

    fun draw() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawColor(Color.LTGRAY)
            placedBombs.forEach { it.draw(canvas) }
            placedBlasts.forEach { it.draw(canvas) }
            placedTargets.forEach { it.draw(canvas) }
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            placeBomb(event.x, event.y)
        }
        return true
    }
}